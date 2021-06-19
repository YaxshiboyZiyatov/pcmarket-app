package com.example.pcmarketuz.repository;

import com.example.pcmarketuz.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
   boolean existsByName(String name);
}
