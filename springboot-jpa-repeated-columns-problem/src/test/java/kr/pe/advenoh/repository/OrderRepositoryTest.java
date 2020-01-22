package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.entity.working.Item;
import kr.pe.advenoh.model.entity.working.Order;
import kr.pe.advenoh.model.entity.working.OrderItemMapping;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {
	@PersistenceContext EntityManager em;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ItemRepository itemRepository;


	@Test
	@Transactional
	public void save_order() {
		Order order = new Order();
		order.setOrderDate(new Date());

		orderRepository.save(order);

		List<Order> orders = orderRepository.findAll();

		log.info("orders : {}", orders);
		assertThat(orders.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	public void save_item() {
		Item item = new Item("item1", 3, 123);

		itemRepository.save(item);

		List<Item> items = itemRepository.findAll();

		log.info("items : {}", items);
		assertThat(items.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	public void save_orderItemMapping() {
		Order order = new Order();
		order.setOrderDate(new Date());

		orderRepository.save(order);

		Item item = new Item("item1", 3, 123);

		itemRepository.save(item);

		OrderItemMapping orderItemMapping = new OrderItemMapping();
		orderItemMapping.setItem(item);
		orderItemMapping.setCount(23);
		orderItemMapping.setOrderPrice(2323);
		orderItemMapping.setOrder(order);

		orderItemRepository.save(orderItemMapping);

		List<OrderItemMapping> orderItemMappings = orderItemRepository.findAll();
		assertThat(orderItemMappings.get(0).getCount()).isEqualTo(23);

		log.info("orderItemMappings : {}", orderItemMappings);

	}
}