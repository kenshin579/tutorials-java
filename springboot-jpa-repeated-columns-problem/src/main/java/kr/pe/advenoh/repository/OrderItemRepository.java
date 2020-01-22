package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.entity.working.OrderItemMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemMapping, Long> {

}
