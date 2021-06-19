package com.example.pcmarketuz.payload;

import lombok.Data;

@Data
public class ArticleDto {
    private String title;

    private String description;

    private String urlLink;

    private Integer attachmentId;
}
