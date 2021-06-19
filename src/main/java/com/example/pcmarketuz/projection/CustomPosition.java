package com.example.pcmarketuz.projection;

import com.example.pcmarketuz.entity.Position;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Position.class)
public interface CustomPosition {
    Integer getId();

    String getName();
}
