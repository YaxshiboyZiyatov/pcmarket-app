package com.example.pcmarketuz.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    private String model;

    @NotNull(message = "Not empty")
    private Double price;

    private Integer categoryId;


    private Integer attachmentId;
}
