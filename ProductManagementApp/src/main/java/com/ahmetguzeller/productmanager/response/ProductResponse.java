package com.ahmetguzeller.productmanager.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductResponse {

    private Integer productId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Date productUpdatedDate;
    private Date productCreatedDate;

}
