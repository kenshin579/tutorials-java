package kr.pe.advenoh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import kr.pe.advenoh.model.audit.DateAudit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

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

    @JsonIgnore //JSON 변환시 무한 루프 방지용
//    @BatchSize(size = 2) //batch size를 지정한다
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> commentList = Lists.newArrayList();

    @Builder
    public Post(String title, String author, int likeCount, String content) {
        this.title = title;
        this.author = author;
        this.likeCount = likeCount;
        this.content = content;
    }
}