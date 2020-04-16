package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberSupportRepository {
}
