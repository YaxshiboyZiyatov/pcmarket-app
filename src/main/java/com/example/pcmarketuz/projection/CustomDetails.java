package com.example.pcmarketuz.projection;

import com.example.pcmarketuz.entity.Details;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Details.class)
public interface CustomDetails {
    Integer getId();

    String getName();
}
