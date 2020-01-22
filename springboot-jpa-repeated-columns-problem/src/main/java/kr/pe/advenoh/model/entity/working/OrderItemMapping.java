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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "test_order_item")
public class OrderItemMapping extends DateAudit {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_item_seqno")
	private Long orderItemNo;

	@ManyToOne
	@JoinColumn(name = "item_seqno")
	private Item item;      //주문 상품

	@ManyToOne
	@JoinColumn(name = "order_seqno")
	private Order order;    //주문

	private int orderPrice; //주문 가격
	private int count;      //주문 수량
}
