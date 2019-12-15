package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Comment;
import kr.pe.advenoh.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select c from Comment c")
    List<Comment> findAllComments();

    @Query("select p from Post p left join fetch p.commentList")
    List<Post> findAllWithFetchJoin();
}