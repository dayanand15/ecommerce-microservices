package com.deen.product_service.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deen.product_service.dto.ApiResponse;
import com.deen.product_service.dto.ProductRequest;
import com.deen.product_service.dto.ProductResponse;
import com.deen.product_service.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  
  @PostMapping("/create")
  public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest){
    ProductResponse productResponse = productService.createProduct(productRequest);

    return new ApiResponse<>(
      LocalDateTime.now(),
      201, 
      "Product created successfully",
      productResponse
    );
  }

  @GetMapping()
  public ApiResponse<List<ProductResponse>> getAllProducts(){
    List<ProductResponse> productResponse= productService.getAllProducts();

    return new ApiResponse<List<ProductResponse>>
    ( LocalDateTime.now(),
      200, 
      "Product fetched successfully",
      productResponse
    );
  }

  @GetMapping("/{product_id}")
  public ApiResponse<ProductResponse> getProduct(@PathVariable Long product_id){
    ProductResponse productResponse= productService.getProductById(product_id);

     return new ApiResponse<ProductResponse>
    ( LocalDateTime.now(),
      200, 
      "Product fetched successfully",
      productResponse
    ); 
  }

  @PutMapping("/{product_id}")
  public ApiResponse<ProductResponse> updateProduct(@PathVariable Long product_id,@Valid @RequestBody ProductRequest productRequest){
    ProductResponse productResponse= productService.updateProduct(product_id, productRequest);

     return new ApiResponse<ProductResponse>
    ( LocalDateTime.now(),
      200, 
      "Product updated successfully",
      productResponse
    );
  }

  @DeleteMapping("/{product_id}")
    public ApiResponse<String> deleteProduct(@PathVariable Long product_id){
        productService.deleteProduct(product_id);

        return new ApiResponse<String> 
    ( LocalDateTime.now(),
      200, 
      "Product deleted successfully",
      "Product deleted successfully"
    );
    }

}

