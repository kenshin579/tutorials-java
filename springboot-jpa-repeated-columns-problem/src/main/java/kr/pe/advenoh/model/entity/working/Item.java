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
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "test_item")
public class Item extends DateAudit {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_seqno")
	private Long itemNo;

	private String name;        //이름
	private int price;          //가격
	private int stockQuantity;  //재고수량

	public Item(String name, int price, int stockQuantity) {
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
}
