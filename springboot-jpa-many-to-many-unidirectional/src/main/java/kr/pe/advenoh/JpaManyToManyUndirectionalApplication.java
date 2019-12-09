package kr.pe.advenoh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpaManyToManyUndirectionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaManyToManyUndirectionalApplication.class, args);
	}

}
