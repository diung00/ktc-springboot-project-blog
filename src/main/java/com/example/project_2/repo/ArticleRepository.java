package com.example.project_2.repo;

import com.example.project_2.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository
        extends JpaRepository<Article, Long> {
}
