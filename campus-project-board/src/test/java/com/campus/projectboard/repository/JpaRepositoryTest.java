package com.campus.projectboard.repository;

import static org.assertj.core.api.Assertions.*;

import com.campus.projectboard.config.JpaConfig;
import com.campus.projectboard.domain.Article;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
  private final ArticleRepository articleRepository;
  private final ArticleCommentRepository articleCommentRepository;

  JpaRepositoryTest(
      @Autowired ArticleRepository articleRepository,
      @Autowired ArticleCommentRepository articleCommentRepository
  ) {
    this.articleRepository = articleRepository;
    this.articleCommentRepository = articleCommentRepository;
  }

  @DisplayName("select 테스트")
  @Test
  void givenTestData_whenSelecting_thenWorksFine() {
    // Given

    // When
    List<Article> articles = articleRepository.findAll();

    // Then
    assertThat(articles)
        .isNotNull()
        .hasSize(123); // classpath:resources/data.sql 참조
  }

  @DisplayName("insert 테스트")
  @Test
  void givenTestData_whenInserting_thenWorksFine() {
    // Given
    long previousCount = articleRepository.count();
    Article article = Article.of("new article", "new content", "new hashtag");

    // When
    articleRepository.save(article);

    // Then
    assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
  }

  @DisplayName("update 테스트")
  @Test
  void givenTestData_whenUpdating_thenWorksFine() {
    // Given
    Article article = articleRepository.findById(1L).orElseThrow();

    // When
    Article savedArticle = articleRepository.saveAndFlush(article);

    // Then
//    assertThat(savedArticle.getHashtags())
//        .hasSize(1)
//        .extracting("hashtagName", String.class)
//        .containsExactly(updatedHashtag.getHashtagName());
  }

  @DisplayName("delete 테스트")
  @Test
  void givenTestData_whenDeleting_thenWorksFine() {
    // Given
    Article article = articleRepository.findById(1L).orElseThrow();
    long previousArticleCount = articleRepository.count();
    long previousArticleCommentCount = articleCommentRepository.count();
    int deletedCommentsSize = article.getArticleComments().size();

    // When
    articleRepository.delete(article);

    // Then
    assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
    assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);
  }
}