package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * todo: 작업이 더 필요함.
 * https://www.baeldung.com/spring-order
 *
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class RatingRetrieverUnitTest {
	@Autowired
	private List<Rating> ratings;

	@Test
	public void givenOrder_whenInjected_thenByOrderValue() {
		assertThat(ratings.get(0).getRating()).isEqualTo(1);
		assertThat(ratings.get(1).getRating()).isEqualTo(2);
		assertThat(ratings.get(2).getRating()).isEqualTo(3);
	}
}
