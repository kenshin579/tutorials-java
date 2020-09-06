package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Comment;
import kr.pe.advenoh.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
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

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void post1_comment1_저장후_author로_확인하() {
        create_post1_comment1();

        Comment comment = commentRepository.findByAuthor("angela");
        assertThat(comment.getPost().getTitle()).isEqualTo("title1");
    }

    @Test
    public void 연관관계_수정() {
        create_post1_comment1();

        //포스트2 변경
        Post post2 = new Post("title2", "frank2", 3, "content");
        testEntityManager.persist(post2);

        //새로운 연관관계 맺
        Comment comment = commentRepository.findByAuthor("angela");
        comment.setPost(post2);
        testEntityManager.persist(comment);

        comment = commentRepository.findByAuthor("angela");
        assertThat(comment.getPost().getTitle()).isEqualTo("title2");
    }

    @Test
    public void 연관관계_제거() {
        create_post1_comment1();
        Comment comment = commentRepository.findByAuthor("angela");
        comment.setPost(null); //연관관계 제거
        testEntityManager.persistAndFlush(comment); //update comment set updated_dt=?, author=?, content=?, post_id=? where id=?

        assertThat(comment.getPost()).isNull();
    }

    /**
     * todo : 연관된 엔티티 삭제시 오류가 발생하도록 작성해보기 pg 178
     */
    @Test
    public void 연관관계_제거_오류() {

        Pair<Post, Comment> pair = create_post1_comment1();

        Comment comment2 = new Comment("angela2", "comment2");
        comment2.setPost(pair.getKey());
        testEntityManager.persistAndFlush(comment2);

        Comment comment = commentRepository.findByAuthor("angela");
        comment.setPost(null); //연관관계 제거
        testEntityManager.persistAndFlush(comment);

//        Comment comment = commentRepository.findByAuthor("angela");
//        comment.setPost(null); //연관관계 제
        testEntityManager.remove(pair.getKey());
    }

    private Pair<Post, Comment> create_post1_comment1() {
        //포스트1 저장
        Post post1 = new Post("title1", "frank1", 5, "content");
        testEntityManager.persistAndFlush(post1);

        //코멘트1 저장
        Comment comment1 = new Comment("angela", "comment1");
        comment1.setPost(post1); //연관관계 설정
        testEntityManager.persistAndFlush(comment1);

        return Pair.of(post1, comment1);
    }
}