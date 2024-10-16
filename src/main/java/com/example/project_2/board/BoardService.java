package com.example.project_2.board;

import com.example.project_2.model.Board;
import com.example.project_2.repo.ArticleRepository;
import com.example.project_2.repo.BoardRepository;
import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void insertBoards() {
        List<Board> boards = List.of(
                new Board("자유 게시판"),
                new Board("개발 게시판"),
                new Board("일상 게시판"),
                new Board("사건사고 게시판")
        );
        repository.saveAll(boards);
        System.out.println("Inserted boards: " + boards);
    }

    public List<Board> readAll(){
        return repository.findAll();
    }

    public Board readOne(Long id){
        return repository.findById(id).orElse(null);
    }

}
