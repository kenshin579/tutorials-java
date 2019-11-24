package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Comment;
import kr.pe.advenoh.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void post1_comment1_저장후_author로_확인하() {
        //포스트1 저장
        Post post1 = new Post("title1", "frank1", 5, "content");
        testEntityManager.persist(post1);

        //코멘트1 저장
        Comment comment1 = new Comment("angela", "comment1");
        comment1.setPost(post1); //연관관계 설정
        testEntityManager.persist(comment1);

        Comment comment = commentRepository.findByAuthor("angela");
        assertThat(comment.getPost().getTitle()).isEqualTo("title1");
    }

    /**
     * todo : 조회할 때 DB에서 조회하는 건가?
     */
    @Test
    public void 연관관계_수정() {
        //포스트1 저장
        Post post1 = new Post("title1", "frank1", 5, "content");
        testEntityManager.persist(post1);

        //코멘트1 저장
        Comment comment1 = new Comment("angela1", "comment1");
        comment1.setPost(post1); //연관관계 설정
        testEntityManager.persist(comment1);

        //포스트2 변경
        Post post2 = new Post("title2", "frank2", 3, "content");
        testEntityManager.persist(post2);

        Comment comment = commentRepository.findByAuthor("angela1");
        comment.setPost(post2);
        testEntityManager.persist(comment);

        comment = commentRepository.findByAuthor("angela1");
        assertThat(comment.getPost().getTitle()).isEqualTo("title2");
    }
}