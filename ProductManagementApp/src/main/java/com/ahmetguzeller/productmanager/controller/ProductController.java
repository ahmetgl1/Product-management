package com.ahmetguzeller.productmanager.controller;

import com.ahmetguzeller.productmanager.model.Product;
import com.ahmetguzeller.productmanager.request.CreateProductRequest;
import com.ahmetguzeller.productmanager.request.UpdateProductRequest;
import com.ahmetguzeller.productmanager.response.ProductResponse;
import com.ahmetguzeller.productmanager.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest createProductRequest){

        return ResponseEntity.ok(productService.createProduct(createProductRequest));
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer productId){

return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PutMapping("/update/{productId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer productId,
                                                         @RequestBody UpdateProductRequest updateProductRequest){
        return ResponseEntity.ok(productService.updateProduct(productId, updateProductRequest));
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<ProductResponse>> getAllByProductIdAndNotDeleted(){
        return ResponseEntity.ok(productService.getAllByProductIdAndNotDeleted());
    }

    @DeleteMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteProductById(@PathVariable Integer productId){

        productService.deleteProduct(productId);

        return ResponseEntity.ok()
                                  .build();
    }









}
