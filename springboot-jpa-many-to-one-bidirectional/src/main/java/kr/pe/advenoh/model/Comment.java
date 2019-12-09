package kr.pe.advenoh.model;

import kr.pe.advenoh.model.audit.DateAudit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "post")
@Entity
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
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; //연관관계의 주인이 된다

    @Builder
    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
    }

    /**
     * 편의 메서드
     * - 양쪽에 작성하는 경우에는 무한루프에 빠지지 않도록 작성해야 함
     */
    public void setPost(Post post) {
        this.post = post;
        //무한루프에 빠지지 않도록 체크
        if (!post.getComments().contains(this)) {
            post.getComments().add(this);
        }
    }
}