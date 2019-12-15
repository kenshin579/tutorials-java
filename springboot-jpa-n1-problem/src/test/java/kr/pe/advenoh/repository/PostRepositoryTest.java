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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Test
    public void test_N1_문제_발생_즉시로딩_하는_경우() throws JsonProcessingException {
        savePostWithComments(4, 2);
        List<Post> posts = postRepository.findAll(); //N+1 발생한다
    }

    @Transactional
    @Test
    public void test_N1_문제_발생_지연로딩_하는_경우() throws JsonProcessingException {
        savePostWithComments(4, 2);
        List<Post> posts = postRepository.findAll(); //N+1 발생하지 않는

        Post post = posts.get(0); //쿼리 발생하지 않음.
        log.info("post : {}", post.getCommentList()); //조회 쿼리 이때 발생함
    }

    @Transactional
    @Test
    public void test_N1_문제_발생_지연로딩설정_loop으로_조회하는_경우() throws JsonProcessingException {
        savePostWithComments(4, 2);
        List<Post> posts = postRepository.findAll(); //N+1 발생하지 않는다

        List<Comment> commentList;
        for (Post post : posts) {
             commentList = post.getCommentList();
            log.info("post author: {}", commentList.size()); //N+1 발생한다
        }
    }

    @Transactional
    @Test
    public void test_N1_문제_발생_JPQL로_조회하는_경우() {
        savePostWithComments(4, 2);
        List<Comment> comments = postRepository.findAllComments();
    }

    @Transactional
    @Test
    public void test_N1_문제_해결방법_fetch_join_사용() {
        savePostWithComments(4, 2);
        List<Post> posts = postRepository.findAllWithFetchJoin(); //한번에 조회해온다.

        List<Comment> commentList;
        for (Post post : posts) {
            commentList = post.getCommentList();
            log.info("post author: {}", commentList.size()); //N+1 발생하지 않는다
        }
    }

    @Transactional
    @Test
    public void test_N1_문제_해결방법_증시로딩설정_loop으로_조회하는_경우() throws JsonProcessingException {
        savePostWithComments(4, 2);
        List<Post> posts = postRepository.findAll(); //배치 사이즈만큼 조회해온다
    }

    private void savePostWithComments(int maxPosts, int maxComments) {
        Post post;
        Comment comment;
        for (int i = 1; i <= maxPosts; i++) {
            post = Post.builder()
                    .title("title" + i)
                    .author("frank" + i)
                    .likeCount(5 + i)
                    .content("content" + i).build();
            em.persist(post);

            for (int j = 1; j <= maxComments; j++) {
                comment = Comment.builder()
                        .author("commentAuthor" + j)
                        .content("content" + j)
                        .build();
                comment.setPost(post);
                em.persist(comment);
            }
        }
        em.flush();
        em.clear();
    }
}