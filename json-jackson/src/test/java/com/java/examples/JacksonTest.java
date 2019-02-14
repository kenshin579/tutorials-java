package com.java.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

@Slf4j
public class JacksonTest {

	//todo: 잘 안됨
	@Test
	public void 날짜_포맷없이_생성하는_경우() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String dateStrWithoutFormat = mapper.writeValueAsString(LocalDateTime.now());
		log.info("dateStrWithoutFormat: {}", dateStrWithoutFormat);

//		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String dateStrFormat = mapper.writeValueAsString(LocalDateTime.now());
		log.info("dateStrFormat: {}", dateStrFormat);
	}

}
