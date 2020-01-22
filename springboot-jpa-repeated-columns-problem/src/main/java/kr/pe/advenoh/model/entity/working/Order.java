package kr.pe.advenoh.model.entity.working;

import kr.pe.advenoh.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "order")
public class Order extends DateAudit {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_seqno")
	private Long orderNo;

	private Date orderDate;     //주문시간

	@OneToMany(mappedBy = "order")
	private List<OrderItemMapping> orderItemMappingList = new ArrayList<>();

}
