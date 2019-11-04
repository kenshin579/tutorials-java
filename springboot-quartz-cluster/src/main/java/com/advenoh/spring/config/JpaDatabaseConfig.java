package kr.pe.advenoh.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * https://stackoverflow.com/questions/51467132/spring-webmvctest-with-enablejpa-annotation
 */
@Configuration
@EnableJpaAuditing
public class JpaDatabaseConfig {
}
