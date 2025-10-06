package com.medblogbackend.service;

import com.medblogbackend.entity.Article;
import com.medblogbackend.entity.Categorie;


import java.util.List;


public interface ArticleService {

   Article createArticle(Article article, Long userId);

   List<Article> getAllArticles();

   Article getArticleById(Long id);

   Article updateArticle(Long id, Article article);

   void deleteArticle(Long id);

   List<Article> getArticlesByCategorie(Categorie categorie);

   List<Article> getArticlesByUser(Long userId);

   List<Article> serchArticles(String titre, Categorie categorie);

}
