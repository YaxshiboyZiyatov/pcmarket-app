package com.example.pcmarketuz.projection;

import com.example.pcmarketuz.entity.Supplier;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Supplier.class)
public interface CustomSupplier {
    Integer getId();

    String getFullName();

    String getPhoneNumber();

    String getMassageBody();

}
