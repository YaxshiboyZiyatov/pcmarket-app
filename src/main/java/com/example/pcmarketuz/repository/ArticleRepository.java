package com.example.pcmarketuz.repository;

import com.example.pcmarketuz.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    boolean existsByUrlLink(String urlLink);
}
