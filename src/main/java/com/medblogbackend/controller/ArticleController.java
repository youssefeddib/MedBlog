package com.medblogbackend.controller;


import com.medblogbackend.entity.Article;
import com.medblogbackend.entity.Categorie;
import com.medblogbackend.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "*")
public class ArticleController {

    private ArticleService articleService;
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("create")
    public ResponseEntity<Article> createArticle(@RequestBody Article article, @RequestParam Long userId) {
        Article createdArticle = articleService.createArticle(article, userId);
        return ResponseEntity.ok(createdArticle);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        return ResponseEntity.ok(articleService.updateArticle(id, article));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Article>> getArticlesByCategorie(@PathVariable Categorie categorie){
        return ResponseEntity.ok(articleService.getArticlesByCategorie(categorie));
    }

    @GetMapping("auteur/{user Id")
    public ResponseEntity<List<Article>> getArticlesByUser(@PathVariable Long userId) {
        return  ResponseEntity.ok(articlesService.getArticleByUser(userId))
    }
}
