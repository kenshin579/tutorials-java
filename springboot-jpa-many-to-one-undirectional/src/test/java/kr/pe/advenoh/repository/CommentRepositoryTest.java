package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Comment;
import kr.pe.advenoh.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void save_post_comment_확인() {
        Post post = postRepository.save(Post.builder()
                .title("title")
                .author("postAuthor")
                .likeCount(5)
                .build());

        Comment comment = Comment.builder()
                .author("frank")
                .content("content").build();
        comment.setPost(post);

        commentRepository.save(comment);
        List<Comment> comments = commentRepository.findAll();

        assertThat(comments.get(0).getAuthor()).isEqualTo("frank");
        assertThat(comments.get(0).getPost().getAuthor()).isEqualTo("postAuthor");
    }
}