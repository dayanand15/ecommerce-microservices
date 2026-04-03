package com.deen.product_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deen.product_service.dto.ProductRequest;
import com.deen.product_service.dto.ProductResponse;
import com.deen.product_service.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  
  @PostMapping("/create")
  public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
    return productService.createProduct(productRequest);
  }

  @GetMapping()
  public List<ProductResponse> getAllProducts(){
    return productService.getAllProducts();
  }

  @GetMapping("/{product_id}")
  public ProductResponse getProduct(@PathVariable Long product_id){
    return productService.getProductById(product_id);
  }

  @PutMapping("/{product_id}")
  public ProductResponse updateProduct(@PathVariable Long product_id, @RequestBody ProductRequest productRequest){
    return productService.updateProduct(product_id, productRequest);
  }

  @DeleteMapping("/{product_id}")
    public void deleteProduct(@PathVariable Long product_id){
       productService.deleteProduct(product_id);
    }

}

