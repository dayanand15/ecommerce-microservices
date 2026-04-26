package com.deen.product_service.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  
  private static final Logger log=LoggerFactory.getLogger(ProductService.class);

  private final ProductRepository productRepository;

  public ProductResponse createProduct(ProductRequest productRequest){
    log.info("Creating product with name: {}", productRequest.getName());

    Product product=new Product();

    product.setName(productRequest.getName());
    product.setDescription(productRequest.getDescription());
    product.setPrice(productRequest .getPrice());
    product.setCategory(productRequest.getCategory());
    product.setStockQuantity(productRequest.getStockQuantity());

    Product saved = productRepository.save(product);
    log.info("Product created successfully with product id: {]", saved.getProductId());
    
    return new ProductResponse(
      saved.getProductId(),
      saved.getName(),
      saved.getDescription(), 
      saved.getPrice(),
      saved.getStockQuantity(),
      saved.getCategory()
    );
  }


  public List<ProductResponse> getAllProducts(){
    log.info("Fetching all products");

     List<ProductResponse> allProducts=productRepository
              .findAll()
              .stream()
              .map(product -> new ProductResponse(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
              product.getCategory()                
              ))
              .toList();
      log.info("Total products found: {}",allProducts.size());

    return allProducts;
  }

  public ProductResponse getProductById(long productId){
    log.info("Fetching product with id: {}", productId);
    Product product = productRepository.findById(productId)
              .orElseThrow(() ->  new ProductNotFoundException("Product not found with id: " + productId));
    
    return new ProductResponse(
      product.getProductId(),
      product.getName(),
      product.getDescription(),
      product.getPrice(),
      product.getStockQuantity(),
      product.getCategory());
  }

  public ProductResponse updateProduct(Long productId,ProductRequest productRequest){
    log.info("Updating product with id: {}",productId);
    Product product=productRepository.findById(productId)
                      .orElseThrow(() -> new  ProductNotFoundException("Product not found with id: "+ productId));

    product.setName(productRequest.getName());
    product.setDescription(productRequest.getDescription());
    product.setPrice(productRequest.getPrice());
    product.setStockQuantity(productRequest.getStockQuantity());
    product.setCategory(productRequest.getCategory());

    Product updated=productRepository.save(product);

    log.info("Product updated successfully with id: {}", productId);
    return new ProductResponse(
      updated.getProductId(),
      updated.getName(),
      updated.getDescription(),
      updated.getPrice(),
      updated.getStockQuantity(),
      updated.getCategory()
    );
  }

  public void deleteProduct(Long productId){
    log.info("Deleting product with id: {}",productId);
    Product product=productRepository.findById(productId)
              .orElseThrow(() -> new ProductNotFoundException("Product not found with id: to delete "+ productId));

    productRepository.delete(product);
    log.info("Product deleted successfully with id: {}",productId);
  }
}
