package com.campus.projectboard.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
    @Index(columnList = "content"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @ManyToOne(optional = false)
  private Article article; // 게시글 (ID)

  private String content; // 본문

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt; // 생성일시

  @CreatedBy
  @Column(nullable = false, updatable = false, length = 100)
  private String createdBy; // 생성자

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @LastModifiedDate
  @Column(nullable = false)
  protected LocalDateTime modifiedAt; // 수정일시

  @LastModifiedBy
  @Column(nullable = false, length = 100)
  protected String modifiedBy; // 수정자

  protected ArticleComment() {}

  private ArticleComment(Article article, String content) {
    this.article = article;
    this.content = content;
  }

  public static ArticleComment of(Article article, String content) {
    return new ArticleComment(article, content);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ArticleComment that)) return false;
    return this.getId() != null && this.getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId());
  }

}
