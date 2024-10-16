package com.example.project_2.comment;

import com.example.project_2.model.Article;
import com.example.project_2.model.Comment;
import com.example.project_2.repo.ArticleRepository;
import com.example.project_2.repo.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final ArticleRepository articleRepo;
    private final CommentRepository commentRepo;

    public CommentService(
            ArticleRepository articleRepo,
            CommentRepository commentRepo
    ) {
        this.articleRepo = articleRepo;
        this.commentRepo = commentRepo;
    }

    // Create
    public Comment create(
            Long articleId,
            String content,
            String writer,
            String password
    ) {
        Article article = articleRepo.findById(articleId)
                .orElseThrow();

        Comment comment = new Comment(
                content,
                writer,
                article,
                password

        );
        return commentRepo.save(comment);
    }


    public Comment readOne(
            Long articleId,
            Long commentId
    ) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow();

        if (!comment.getArticle().getId().equals(articleId))
            throw new RuntimeException();

        return comment;
    }

    public List<Comment> readAll (){
        return commentRepo.findAll();
    }

    public Comment update (
            Long articleId,
            Long commentId,
            String writer,
            String content
    ){
        Optional<Comment> cmt = commentRepo.findById(articleId);

        if (cmt.isEmpty()){
            return null;
        }
        Comment comment = cmt.get();
        if (!comment.getArticle().getId().equals(articleId)){
            throw new RuntimeException();
        }

        comment.setWriter(writer);
        comment.setContent(content);
         return commentRepo.save(comment);
    }

    public void delete(Long id){ commentRepo.deleteById(id);}


    public boolean passCompare(
            Long id,
            String inputPassword
    ){
        Comment cmt = commentRepo.findById(id).orElse(null);
        if (cmt == null || !cmt.getPassword().equals(inputPassword)){
            return false;
        }
        return true;
    }

}
