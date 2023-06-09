package com.ahmetguzeller.productmanager.components;

import com.ahmetguzeller.productmanager.model.Product;
import com.ahmetguzeller.productmanager.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {


 public ProductResponse convert(Product product){

     ProductResponse productResponse = ProductResponse.builder()

             .productId(product.getProductId())
             .productName(product.getProductName())
             .productPrice(product.getProductPrice())
            // .isDeleted(false)
             .productCreatedDate(product.getProductCreatedDate())
             .productUpdatedDate(product.getProductUpdatedDate())
             .quantity(product.getQuantity())
             .build();

     return productResponse;
 }







}
