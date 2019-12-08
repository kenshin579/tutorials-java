package kr.pe.advenoh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.pe.advenoh.model.audit.DateAudit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
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

    //양방향 연관관계 설정
    @JsonIgnore //JSON 변환시 무한 루프 방지용
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title, String author, int likeCount, String content) {
        this.title = title;
        this.author = author;
        this.likeCount = likeCount;
        this.content = content;
    }

    /**
     * 편의 메서드
     * - 양쪽에 작성하는 경우에는 무한루프에 빠지지 않도록 작성해야 함
     *
     * @param comment
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
        //무한루프에 빠지지 않도록 체크
        if (comment.getPost() != this) {
            comment.setPost(this);
        }
    }
}