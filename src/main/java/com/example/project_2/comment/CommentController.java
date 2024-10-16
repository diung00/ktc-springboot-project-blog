package com.example.project_2.comment;


import com.example.project_2.article.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("articles/{articleId}/comments")
public class CommentController {
    private final ArticleService articleService;
    private final CommentService service;

    public CommentController(
            ArticleService articleService,
            CommentService service
    ) {
        this.articleService = articleService;
        this.service = service;
    }

    @PostMapping("create")
    public String create(
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer,
            @RequestParam("password")
            String password
    ) {
        service.create(articleId, content, writer, password);
        return String.format("redirect:/articles/%d", articleId);
    }

    @GetMapping("{cmtId}/update")
    public String cmtUpdateView(
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("cmtId")
            Long cmtId,
            Model model
    ){
        model.addAttribute("articles", articleService.readAll());
        model.addAttribute("article", articleService.readOne(articleId));
        model.addAttribute("comment", service.readOne(articleId, cmtId));
        return "comments/update.html";
    }

    @PostMapping("{cmtId}/update")
    public String update(
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("cmtId")
            Long cmtId,
            @RequestParam("writer")
            String writer,
            @RequestParam("content")
            String content,
            @RequestParam("inputPassword")
            String inputPassword,
            Model model
    ){
        boolean passIsCorrect = service.passCompare(cmtId, inputPassword);
        if (!passIsCorrect){
            model.addAttribute("errorMessage", "비밀번호 틀렸습니다!");
            return "articles/passIncorrect.html";
        }
        service.update(articleId,cmtId,writer,content);
        return String.format("redirect:/articles/%d", articleId);
    }

    @GetMapping("{cmtId}/delete")
    public String deleteView(
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("cmtId")
            Long cmtId,
            Model model
    ){
        model.addAttribute("article", articleService.readOne(articleId));
        model.addAttribute("comment", service.readOne(articleId, cmtId));
        return "comments/delete.html";
    }

    @PostMapping("{cmtId}/delete")
    public String delete(
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("cmtId")
            Long cmtId,
            @RequestParam("inputPassword")
            String inputPassword,
            Model model


    ){
        boolean passwordIsCorrect = service.passCompare(cmtId, inputPassword);
        if (!passwordIsCorrect) {
            model.addAttribute("errorMessage", "비밀번호 틀렸습니다.");
            return "articles/passIncorrect.html";
        }
        service.delete(cmtId);
        return String.format("redirect:/articles/%d", articleId);
    }


}

















