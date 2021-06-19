package com.example.pcmarketuz.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BasketDto {

    @NotNull(message = "Not empty")
    private String allSum;

    private Integer userId;

    private Integer outputProductId;
}
