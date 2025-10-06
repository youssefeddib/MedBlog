package com.medblogbackend.serviceimpl;

import com.medblogbackend.entity.Article;
import com.medblogbackend.entity.Categorie;
import com.medblogbackend.entity.User;
import com.medblogbackend.repository.ArticleRepository;
import com.medblogbackend.repository.UserRepository;
import com.medblogbackend.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Article createArticle (Article article, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        article.setAuteur(user);
        return articleRepository.save(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Article non trové"));
    }

    @Override
    public Article updateArticle(Long id, Article article) {
        Article artticle = getArticleById(id);
        artticle.setTitre(article.getTitre());
        artticle.setContenu(article.getContenu());
        artticle.setCategorie(article.getCategorie());
        artticle.setImageUrl(article.getImageUrl());
        return articleRepository.save(artticle);
    }

    @Override
    public  void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> getArticlesByCategorie(Categorie categorie){
        return articleRepository.findByCategorie(categorie);
    }

    @Override
    public List<Article> getArticlesByUser(Long userId ) {
        return articleRepository.findByAuteurId(userId);
    }

    @Override
    public List<Article> serchArticles(String titre, Categorie categorie) {
        if (titre != null && !titre.isEmpty() && categorie != null) {
            return articleRepository.findByTitreContainingAndCategorie(titre, categorie);
        } else if (titre != null && !titre.isEmpty()) {
            return articleRepository.findByTitreContaining(titre);
        } else if (categorie != null) {
            return articleRepository.findByCategorie(categorie);
        }else {
            return articleRepository.findAll();
        }
    }

}
