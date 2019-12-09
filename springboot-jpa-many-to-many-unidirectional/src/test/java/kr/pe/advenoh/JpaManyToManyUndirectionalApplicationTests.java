package kr.pe.advenoh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JpaManyToManyUndirectionalApplicationTests {
	@Autowired
	private Environment env;

	@Test
	void contextLoads() {
	}

	@Test
	void test_properties() {
		assertThat(env.getProperty("spring.jpa.database")).isEqualTo("h2");
		assertThat(env.getProperty("spring.jpa.show-sql")).isEqualTo("true");
	}
}
