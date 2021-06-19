package com.example.pcmarketuz.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderDto {
    private Timestamp date;

    private String details;


    private Integer basketId;
}
