package com.example.project_2.repo;

import com.example.project_2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository
extends JpaRepository<Comment, Long> {

}
