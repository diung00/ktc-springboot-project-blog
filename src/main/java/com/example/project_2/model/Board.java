package com.example.project_2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

    @Data
    @Entity
    public class Board {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        private String board_title;


        @OneToMany(mappedBy = "board")
        private List<Article> articleList;


    }
