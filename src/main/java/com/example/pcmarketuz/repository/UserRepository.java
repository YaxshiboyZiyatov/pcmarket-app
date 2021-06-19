package com.example.pcmarketuz.repository;

import com.example.pcmarketuz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmailAndPassword(String email, String password);
}
