package kr.pe.advenoh.model;

import kr.pe.advenoh.model.audit.DateAudit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    private String author;

    @Lob
    private String content;

    //연관관계 매팽
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Builder
    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
    }
}