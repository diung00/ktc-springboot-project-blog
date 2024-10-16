package com.example.project_2.article;

import com.example.project_2.board.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("articles")
public class ArticleController {
    private final ArticleService service;
    private final BoardService boardService;

    public ArticleController(
            ArticleService service,
            BoardService boardService) {
        this.service = service;
        this.boardService = boardService;
    }

    @GetMapping
    public String readAll(Model model) {
     model.addAttribute("boards", boardService.readAll());
     model.addAttribute("articleList", service.readAll());
        return "articles/home.html";
    }




    @GetMapping("create")
    public String createView(Model model){
        model.addAttribute("boards", boardService.readAll());

        return "articles/create.html";
    }

    @PostMapping("create")
    public String create(

            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer,
            @RequestParam("password")
            String password,
            @RequestParam ("boardId")
            Long boardId

    ){

        Long id = service.create(title, content, writer,password, boardId).getId();
        return String.format("redirect:/articles/%d", id);
    }

    @GetMapping("{id}")
    public String readOne(
            @PathVariable("id")
            Long id,
            Model model

    ){
        model.addAttribute("article", service.readOne(id));
        return "articles/read.html";
    }

    @GetMapping("{id}/update")
    public String updateView(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("boards", boardService.readAll());
        model.addAttribute("article", service.readOne(id));
        return "articles/update.html";
    }



    @PostMapping("{id}/update")
    public String update(
            @PathVariable("id")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer,
            @RequestParam("inputPassword")
            String inputPassword,
            Model model
    ){
        boolean passwordIsCorrect = service.passCompare(id, inputPassword);
        if (!passwordIsCorrect) {
            model.addAttribute("errorMessage", "비밀번호 틀렸습니다!");
            return "articles/passIncorrect.html";
        }
        service.update(id, title, content, writer);
        return String.format("redirect:/articles/%d", id);

    }



    @GetMapping("{id}/delete")
    public String deleteView(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("article", service.readOne(id));
        return "articles/delete.html";
    }

    @PostMapping("{id}/delete")
    public String delete(
            @PathVariable("id")
            Long id,
            @RequestParam("inputPassword")
            String password,
            Model model


    ){
        boolean passwordIsCorrect = service.passCompare(id, password);
        if (!passwordIsCorrect) {
            model.addAttribute("errorMessage", "비밀번호 틀렸습니다.");
            return "articles/passIncorrect.html";
        }
        service.delete(id);
        return "redirect:/articles";
    }


}
