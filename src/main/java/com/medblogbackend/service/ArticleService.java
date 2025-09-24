package com.medblogbackend.service;

import com.medblogbackend.entity.Article;
import com.medblogbackend.entity.User;
import com.medblogbackend.repository.ArticleRepository;
import com.medblogbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public Article createArticle (Article article, Long userId) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        article.setAuteur(user);
        return articleRepository.save(article);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Article non trové"));
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        Article artticle = getArticleById(id);
        artticle.setTitre(updatedArticle.getTitre());
        artticle.setContenu(updatedArticle.getContenu());
        artticle.setCategorie(updatedArticle.getCategorie());
        artticle.setImageUrl(updatedArticle.getImageUrl());
        return articleRepository.save(artticle);
    }
}
