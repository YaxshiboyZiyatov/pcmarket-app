package com.example.pcmarketuz.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OutputProductDto {
    @NotNull(message = "Not empty")
    private String amount;

    private Integer productId;
}
