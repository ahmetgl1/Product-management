package com.ahmetguzeller.productmanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product-id")
    private Integer productId;
    @Column(name = "product-name")
    private String productName;
    @Column(name = "product-price")
    private Double productPrice;
    @Column(name = "product-quantity")
    private Integer quantity;
    @Column(name = "updated-date")
    @Builder.Default
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productUpdatedDate = new Date();



    @Builder.Default
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created-date")
    private Date productCreatedDate = new Date();


    @Column(name = "is-deleted")
    private boolean isDeleted;

}

