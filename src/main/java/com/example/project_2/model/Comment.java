package com.example.project_2.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Entity
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String content;
    @Setter
    private String writer;
    @Setter
    @ManyToOne
    private Article article;
    @Setter
    private String password;

    public Comment() {}

    public Comment(String writer, String content, Article article, String password) {
        this.writer = writer;
        this.content = content;
        this.article = article;
        this.password = password;
    }
}
