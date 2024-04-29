package com.campus.projectboard.service;

import com.campus.projectboard.domain.constant.SearchType;
import com.campus.projectboard.dto.ArticleDto;
import com.campus.projectboard.dto.ArticleWithCommentsDto;
import com.campus.projectboard.repository.ArticleRepository;
import java.util.Arrays;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

  private final ArticleRepository articleRepository;

  @Transactional(readOnly = true)
  public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
    if (searchKeyword == null || searchKeyword.isBlank()) {
      return articleRepository.findAll(pageable).map(ArticleDto::from);
    }

    return switch (searchType) {
      case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
      case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
      case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
      case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
      case HASHTAG -> articleRepository.findByHashtagNames(
              Arrays.stream(searchKeyword.split(" ")).toList(),
              pageable
          )
          .map(ArticleDto::from);
    };
  }

  @Transactional(readOnly = true)
  public ArticleWithCommentsDto getArticleWithComments(Long articleId) {
    return articleRepository.findById(articleId)
        .map(ArticleWithCommentsDto::from)
        .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
  }

  @Transactional(readOnly = true)
  public ArticleDto getArticle(Long articleId) {
    return articleRepository.findById(articleId)
        .map(ArticleDto::from)
        .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
  }
  public void saveArticle(ArticleDto dto) {
  }

  public void updateArticle(Long articleId, ArticleDto dto) {
  }
}
