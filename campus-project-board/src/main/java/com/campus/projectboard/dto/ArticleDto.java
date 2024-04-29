package com.campus.projectboard.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleDto(
    LocalDateTime createdAt,
    String createdBy,
    String title,
    String content,
    String hashtag
) {

    public static ArticleDto of(LocalDateTime createdAt, String createdBy,String title, String content, String hashtag) {
        return new ArticleDto(createdAt, createdBy, title, content, hashtag);
    }
}
