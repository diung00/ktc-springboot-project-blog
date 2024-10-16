package com.example.project_2.board;

import com.example.project_2.article.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("boards")
public class BoardController {
    private final BoardService service;
    private final ArticleService articleService;
    public BoardController(
            BoardService service,
            ArticleService articleService

    ){
        this.service = service;
        this.articleService = articleService;
    }

    @GetMapping
    public String readAll(Model model){
        model.addAttribute("boards", service.readAll());

        return "boards/home";

    }

    @GetMapping("{id}")
    public String readOne(

            @PathVariable("id")
            Long id,
            Model model
    ) {
        model.addAttribute("boardTitle", service.readOne(id));
        return "boards/read";
    }



}
