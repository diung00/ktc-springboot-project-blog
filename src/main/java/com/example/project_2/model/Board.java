package com.example.project_2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String boardTitle;
    @Setter
    @OneToMany(mappedBy = "board")
    private List<Article> articleList;


    public Board(String boardTitle) {
        this.boardTitle = boardTitle;
    }
}
