package com.ahmetguzeller.productmanager.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class CreateProductRequest {

    private String productName;
    private Double productPrice;
    private Integer quantity;
}



