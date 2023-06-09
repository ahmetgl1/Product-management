package com.ahmetguzeller.productmanager.request;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class UpdateProductRequest {

    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Date updateProductDate;






}
