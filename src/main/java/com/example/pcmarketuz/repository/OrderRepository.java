package com.example.pcmarketuz.repository;

import com.example.pcmarketuz.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
