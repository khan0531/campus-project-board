package com.campus.projectboard.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.campus.projectboard.dto.ArticleDto;
import com.campus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

  @InjectMocks
  private ArticleService sut;

  @Mock
  private ArticleRepository articleRepository;

  @DisplayName("검색어 없이 게시글을 검색하면, 게시글 페이지를 반환한다.")
  @Test
  void givenNoSearchParameters_whenSearchingArticles_thenReturnsArticlePage() {
    // Given
    Pageable pageable = Pageable.ofSize(20);
    given(articleRepository.findAll(pageable)).willReturn(Page.empty());

    // When
    Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

    // Then
    assertThat(articles).isEmpty();
    then(articleRepository).should().findAll(pageable);
  }
}