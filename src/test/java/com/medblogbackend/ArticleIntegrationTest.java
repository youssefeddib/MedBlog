package com.medblogbackend;

import com.medblogbackend.entity.Article;
import com.medblogbackend.entity.Categorie;
import com.medblogbackend.entity.User;
import com.medblogbackend.repository.ArticleRepository;
import com.medblogbackend.repository.UserRepository;
import com.medblogbackend.service.ArticleService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ArticleIntegrationTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreatArticle() {
        User user = new User();
        user.setNom("test");
        user.setPrenom("test");
        user.setEmail("test@gmail.com");
        user.setTelephone("0672891567");

        userRepository.save(user);

        Article article = new Article();
        article.setTitre("test");
        article.setContenu("test");
        article.setCategorie(Categorie.EDUCATION);
        article.setImageUrl("image.png");
        article.setAuteur(user);

        Article saved = articleRepository.save(article);

        assertNotNull(saved.getId());
        assertEquals("test", saved.getTitre());
        assertEquals("test", saved.getContenu());
        assertEquals(Categorie.EDUCATION, saved.getCategorie());
        assertEquals("image.png", saved.getImageUrl());
        assertEquals(user, saved.getAuteur());
    }

    @Test
    public void testUpdateArticle() {
        User user = new User();
        user.setNom("test");
        user.setPrenom("test");
        user.setEmail("test@gmail.com");
        user.setTelephone("0672891567");

        Article article = new Article();
        article.setTitre("test");
        article.setContenu("test");
        article.setCategorie(Categorie.EDUCATION);
        article.setImageUrl("image.png");
        article.setAuteur(user);

        Article saved = articleRepository.save(article);

        saved.setContenu("nouveau contenu");
        articleRepository.save(saved);

        Article updated = articleRepository.findById(saved.getId()).orElse(null);
        assertNotNull(updated);
        assertEquals("nouveau contenu", updated.getContenu());
    }
}
