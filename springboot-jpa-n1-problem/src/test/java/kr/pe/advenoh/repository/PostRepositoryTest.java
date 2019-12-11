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
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void save_post_확인() {
        save_post_comments(5);
        List<Post> posts = postRepository.findAll();

    }

    private void save_post_comments(int maxComments) {
        Post post = postRepository.save(Post.builder()
                .title("title")
                .author("frank")
                .likeCount(5)
                .content("content").build());

        Comment comment;
        for (int i = 1; i < maxComments + 1; i++) {
            comment = Comment.builder()
                    .author("commentAuthor" + i)
                    .content("content" + i)
                    .build();
            comment.setPost(post);
            commentRepository.save(comment);
        }
    }
}