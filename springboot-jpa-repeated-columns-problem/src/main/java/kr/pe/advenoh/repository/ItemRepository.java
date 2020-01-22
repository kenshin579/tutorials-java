package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.entity.working.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
