package com.medblogbackend.repository;


import com.medblogbackend.entity.Article;
import com.medblogbackend.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByCategorie(Categorie categorie);

    List<Article> findByAuteurId(Long userId);
}

