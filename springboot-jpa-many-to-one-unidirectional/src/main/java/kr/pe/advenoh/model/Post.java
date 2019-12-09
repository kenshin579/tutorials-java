package kr.pe.advenoh.model;

import kr.pe.advenoh.model.audit.DateAudit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    private String title;

    private String author;

    @Column(name = "like_count")
    private int likeCount;

    @Lob
    private String content;

    @Builder
    public Post(String title, String author, int likeCount, String content) {
        this.title = title;
        this.author = author;
        this.likeCount = likeCount;
        this.content = content;
    }
}