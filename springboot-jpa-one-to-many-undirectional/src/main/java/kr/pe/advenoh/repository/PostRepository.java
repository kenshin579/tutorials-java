package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}