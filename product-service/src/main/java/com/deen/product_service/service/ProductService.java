package com.deen.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deen.product_service.dto.ProductRequest;
import com.deen.product_service.dto.ProductResponse;
import com.deen.product_service.entity.Product;
import com.deen.product_service.exception.ProductNotFoundException;
import com.deen.product_service.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
  
  private final ProductRepository productRepository;

  public ProductResponse createProduct(ProductRequest productRequest){
    
    Product product=new Product();

    product.setName(productRequest.getName());
    product.setDescription(productRequest.getDescription());
    product.setPrice(productRequest .getPrice());
    product.setCategory(productRequest.getCategory());
    product.setStockQuantity(productRequest.getStockQuantity());

    Product saved = productRepository.save(product);

    return new ProductResponse(
      saved.getProduct_id(),
      saved.getName(),
      saved.getDescription(), 
      saved.getPrice(),
      saved.getStockQuantity(),
      saved.getCategory()
    );
  }


  public List<ProductResponse> getAllProducts(){
    return productRepository.findAll().stream()
              .map(product -> new ProductResponse(
                product.getProduct_id(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
              product.getCategory()                
              ))
              .toList();
  }

  public ProductResponse getProductById(Long product_id){
    Product product=productRepository.findById(product_id)
              .orElseThrow(() ->  new ProductNotFoundException("Product not found with id: "+ product_id));
    
    return new ProductResponse(
      product.getProduct_id(),
      product.getName(),
      product.getDescription(),
      product.getPrice(),
      product.getStockQuantity(),
      product.getCategory());
  }

  public ProductResponse updateProduct(Long product_id,ProductRequest productRequest){
    
    Product product=productRepository.findById(product_id)
                      .orElseThrow(() -> new  ProductNotFoundException("Product not found with id: "+ product_id));

    product.setName(productRequest.getName());
    product.setDescription(productRequest.getDescription());
    product.setPrice(productRequest.getPrice());
    product.setStockQuantity(productRequest.getStockQuantity());
    product.setCategory(productRequest.getCategory());

    Product updated=productRepository.save(product);

    return new ProductResponse(
      updated.getProduct_id(),
      updated.getName(),
      updated.getDescription(),
      updated.getPrice(),
      updated.getStockQuantity(),
      updated.getCategory()
    );
  }

  public void deleteProduct(Long product_id){
    Product product=productRepository.findById(product_id)
              .orElseThrow(() -> new ProductNotFoundException("Product not found with id: to delete "+ product_id));

    productRepository.delete(product);
  }
}
