package com.example.pcmarketuz.repository;

import com.example.pcmarketuz.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByModel(String model);

}
