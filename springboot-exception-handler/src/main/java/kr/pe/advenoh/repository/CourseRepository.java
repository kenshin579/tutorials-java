package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
