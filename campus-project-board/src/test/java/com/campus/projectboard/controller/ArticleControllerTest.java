package com.campus.projectboard.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.campus.projectboard.dto.ArticleDto;
import com.campus.projectboard.dto.ArticleWithCommentsDto;
import com.campus.projectboard.dto.HashtagDto;
import com.campus.projectboard.dto.UserAccountDto;
import com.campus.projectboard.service.ArticleService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

  private final MockMvc mvc;

  @MockBean
  private ArticleService articleService;

  public ArticleControllerTest(MockMvc mvc) {
    this.mvc = mvc;
  }

  @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
  @Test
  void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/articles"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(view().name("articles/index"))
        .andExpect(model().attributeExists("articles"));
  }


  private ArticleDto createArticleDto() {
    return ArticleDto.of(
        createUserAccountDto(),
        "title",
        "content",
        Set.of(HashtagDto.of("java"))
    );
  }

  private ArticleWithCommentsDto createArticleWithCommentsDto() {
    return ArticleWithCommentsDto.of(
        1L,
        createUserAccountDto(),
        Set.of(),
        "title",
        "content",
        Set.of(HashtagDto.of("java")),
        LocalDateTime.now(),
        "uno",
        LocalDateTime.now(),
        "uno"
    );
  }

  private UserAccountDto createUserAccountDto() {
    return UserAccountDto.of(
        "uno",
        "pw",
        "uno@mail.com",
        "Uno",
        "memo",
        LocalDateTime.now(),
        "uno",
        LocalDateTime.now(),
        "uno"
    );
  }

}