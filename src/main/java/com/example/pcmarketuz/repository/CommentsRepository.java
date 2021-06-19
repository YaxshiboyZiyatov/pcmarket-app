package com.example.pcmarketuz.repository;

import com.example.pcmarketuz.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

}
