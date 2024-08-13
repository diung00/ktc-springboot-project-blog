package com.example.project_2;

import com.example.project_2.model.Board;
import com.example.project_2.repo.ArticleRepository;
import com.example.project_2.repo.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository repository;
    private final ArticleRepository articleRepository;
    public BoardService(
            BoardRepository repository,
            ArticleRepository articleRepository

    ){
        this.repository = repository;
        this.articleRepository = articleRepository;
    }

    public List<Board> readAll(){
        return repository.findAll();
    }

    public Board readOne(Long id){
        return repository.findById(id).orElse(null);
    }

}
