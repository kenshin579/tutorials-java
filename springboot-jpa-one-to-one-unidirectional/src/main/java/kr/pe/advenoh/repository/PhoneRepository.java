package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.CelluarPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<CelluarPhone, Long> {
}