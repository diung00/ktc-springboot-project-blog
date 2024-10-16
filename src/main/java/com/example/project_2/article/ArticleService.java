package com.example.project_2.article;

import com.example.project_2.model.Article;
import com.example.project_2.repo.ArticleRepository;
import com.example.project_2.repo.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository repository;
    private final BoardRepository boardRepository;
    public ArticleService(
            ArticleRepository repository,
            BoardRepository boardRepository
            ){
        this.repository = repository;
        this.boardRepository = boardRepository;
    }

    public Article create(

            String title,
            String content,
            String writer,
            String password,
            Long boardId
    ){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setWriter(writer);
        article.setPassword(password);

        article.setBoard(boardRepository.findById(boardId).orElse(null));

        return repository.save(article);
    }

    public List<Article> readAll(){
        return repository.findAll();
    }

    public Article readOne(Long id){
        return repository.findById(id).orElse(null);
    }


    public Article update(
            Long id,
            String title,
            String content,
            String writer
    ){
        Optional<Article> target = repository.findById(id);
        if (target.isEmpty()){
            return null;
        }
        Article target2 = target.get();
        target2.setTitle(title);
        target2.setContent(content);
        target2.setWriter(writer);
        return  repository.save(target2);
    }

     public void delete(Long id){
        repository.deleteById(id);
     }

     public boolean passCompare(
             Long id,
             String inputPassword
     ){
      Article article = repository.findById(id).orElse(null);
      if (article == null || !article.getPassword().equals(inputPassword)){
          return false;
      }
      return true;
     }
}

