package com.example.project_2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String writer;
    @Setter
    private String password;
    @Setter
    @ManyToOne
    private Board board;
    @Setter
    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    public Article() {}

    public Article(String content, String title, String writer, String password, Board board, List<Comment> comments) {
        this.content = content;
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.board = board;
        this.comments = comments;
    }
}
