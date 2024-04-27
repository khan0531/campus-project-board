package com.campus.projectboard.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
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
    @Index(columnList = "title"),
    @Index(columnList = "hashtag"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter @Column(nullable = false)private String title; // 제목
  @Setter @Column(nullable = false, length = 10000) private String content; // 본문

  private String hashtag; // 해시태크

  @ToString.Exclude // ArticleComment 와 양방향 참조
  @OrderBy("id")
  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
  private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

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

  protected Article() {}

  private Article(String title, String content, String hashtag) {
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
  }

  public static Article of(String title, String content, String hashtag) {
    return new Article(title, content, hashtag);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Article that)) return false;
    return this.getId() != null && this.getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId());
  }
}
