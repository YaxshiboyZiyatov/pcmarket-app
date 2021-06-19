package com.example.pcmarketuz.payload;

import lombok.Data;

@Data
public class CommentsDto {
    private String body;

    private String numberStar;

    private Integer userId;
}
