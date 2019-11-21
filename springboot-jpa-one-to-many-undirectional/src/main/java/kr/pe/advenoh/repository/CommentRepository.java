package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}