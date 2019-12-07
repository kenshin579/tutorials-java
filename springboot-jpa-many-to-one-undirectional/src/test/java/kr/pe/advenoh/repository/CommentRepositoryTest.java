package kr.pe.advenoh.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.pe.advenoh.model.Comment;
import kr.pe.advenoh.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
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

    /**
     * @throws JsonProcessingException
     * @DataJpaTest 대신 @SpringBootTest를 사용해야 select 구문이 나옴
     * <p>
     * 1. optional = true : left outer join 을 수행한다.
     * from comment comment0_ left outer join post post1_
     * <p>
     * 2. optional = false
     * from comment comment0_ inner join post post1_
     */
    @Test
    public void save_post_comment_확인_optional_옵션_변경시() throws JsonProcessingException {
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

        Comment foundComment = commentRepository.findById(1L).get();
        log.info("[FRANK] foundComment : {}", new ObjectMapper().writeValueAsString(foundComment));
    }

    @Test
    public void save_post_comment_확인_eager_loading() throws JsonProcessingException {
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

        Comment foundComment = commentRepository.findById(1L).get(); //이때 JOIN해서 Post 객체도 가져온다.
        assertThat(foundComment.getAuthor()).isEqualTo("frank");

        assertThat(foundComment.getPost().getAuthor()).isEqualTo("postAuthor");
    }

    @Transactional
    @Test
    public void save_post_comment_확인_lazy_loading_test() throws JsonProcessingException {
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

        Comment foundComment = commentRepository.findById(1L).get();
        assertThat(foundComment.getAuthor()).isEqualTo("frank");

        Post foundPost = foundComment.getPost(); //(1) 객체 그래프 탐색
        String author = foundPost.getAuthor(); //(2) 팀 객체 실제 사용
        assertThat(author).isEqualTo("postAuthor");
    }
}