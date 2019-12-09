package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByAuthor(String author);
}