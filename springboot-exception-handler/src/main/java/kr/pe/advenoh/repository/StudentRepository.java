package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
