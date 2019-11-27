package kr.pe.advenoh.model;

import kr.pe.advenoh.model.audit.DateAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "author")
	private String author;

	@Lob
	private String content;

	//연관관계 매팽
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post; //연관관계의 주인이 된다

	public Comment(String author, String content) {
		this.author = author;
		this.content = content;
	}

    public void setPost(Post post) {
        this.post = post;
        //기존 관계 제거
        if (this.post != null) {
            this.post.getComments().remove(this);
        }
        post.getComments().add(this);
    }
}