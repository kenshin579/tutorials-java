package kr.pe.advenoh.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.pe.advenoh.model.Comment;
import kr.pe.advenoh.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryIntegrationTest {
//    @Autowired
//    private TestEntityManager testEntityManager;

    @Autowired
    private EntityManager em;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Before
    public void setUp() throws Exception {
    }

    //todo: 여기서부터 다시하면 됨 pg 185
    @Test
    public void 양방향_post1_comment1_저장후_author로_확인하() throws JsonProcessingException {
        create_post1_comment1();

        String jpqlComment = "select c from Comment c";
        String jpqlPost = "select p from Post p";

        List<Comment> commentList = em.createQuery(jpqlComment, Comment.class)
                .getResultList();
        List<Post> postList = em.createQuery(jpqlPost, Post.class)
                .getResultList();
        log.info("[FRANK] commentList: {}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(commentList));
        log.info("[FRANK] postList: {}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(postList));



//        Comment comment = commentRepository.findByAuthor("angela");
//        assertThat(comment.getPost().getTitle()).isEqualTo("title1");
    }

    @Test
    public void 양방향으로_객체_그래프_탐색() throws JsonProcessingException {
        create_post1_comment1();

        //todo: 왜 조회가 안되나?
        Post post = em.find(Post.class, 1L);
        log.info("[FRANK] post : {}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(post));

        List<Comment> comments = post.getComments();
        for (Comment comment : comments) {
            log.info("[FRANK] comment = {}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(comment));
        }
    }
//
//    @Test
//    public void 양방향_연관관계_수정() {
//        create_post1_comment1();
//
//        //포스트2 변경
//        Post post2 = new Post("title2", "frank2", 3, "content");
//        testEntityManager.persist(post2);
//
//        //새로운 연관관계 맺
//        Comment comment = commentRepository.findByAuthor("angela");
//        comment.setPost(post2);
//        testEntityManager.persist(comment);
//
//        comment = commentRepository.findByAuthor("angela");
//        assertThat(comment.getPost().getTitle()).isEqualTo("title2");
//    }
//
//    @Test
//    public void 연관관계_제거() {
//        create_post1_comment1();
//        Comment comment = commentRepository.findByAuthor("angela");
//        comment.setPost(null); //연관관계 제거
//        testEntityManager.persistAndFlush(comment); //update comment set updated_dt=?, author=?, content=?, post_id=? where id=?
//
//        assertThat(comment.getPost()).isNull();
//    }
//
//    /**
//     * todo : 연관된 엔티티 삭제시 오류가 발생하도록 작성해보기 pg 178
//     */
//    @Test
//    public void 연관관계_제거_오류() {
//        Pair<Post, Comment> pair = create_post1_comment1();
//
//        Comment comment2 = new Comment("angela2", "comment2");
//        comment2.setPost(pair.getFirst());
//        testEntityManager.persistAndFlush(comment2);
//
//        Comment comment = commentRepository.findByAuthor("angela");
//        comment.setPost(null); //연관관계 제거
//        testEntityManager.persistAndFlush(comment);
//
////        Comment comment = commentRepository.findByAuthor("angela");
////        comment.setPost(null); //연관관계 제
//        testEntityManager.remove(pair.getFirst());
//    }
//
//    @Test
//    public void 양방향_일대다_방향으로_조회() {
//        Pair<Post, Comment> pair = create_post1_comment1();
//        List<Comment> comments = pair.getFirst().getComments();
//
//        for (Comment comment : comments) {
//            log.info("comment: {}", comment);
//        }
//    }

    private Pair<Post, Comment> create_post1_comment1() {
        //포스트1 저장
        Post post1 = new Post("title1", "frank1", 5, "content");
        em.persist(post1);

        //코멘트1 저장
        Comment comment1 = new Comment("angela", "comment1");
        comment1.setPost(post1); //연관관계 설정
        em.persist(comment1);

        return Pair.of(post1, comment1);
    }
}