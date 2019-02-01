package com.logicbig.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@Import(MyViewConfig.class)
public class MyWebConfig {

	@Bean
	public UserController tradeParamRequestController() {
		return new UserController();
	}
}