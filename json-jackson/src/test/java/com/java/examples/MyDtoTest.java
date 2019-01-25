package com.java.examples;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MyDtoTest {

	/**
	 * https://www.baeldung.com/jackson-ignore-null-fields
	 *
	 * @throws JsonProcessingException
	 */
	@Test
	public void givenNullsIgnoredOnClass_whenWritingObjectWithNullField_thenIgnored() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		MyDto dtoObject = new MyDto();

		String dtoAsString = mapper.writeValueAsString(dtoObject);
		log.debug("dtoAsString: {}", dtoAsString);

		assertThat(dtoAsString, containsString("intValue"));
		assertThat(dtoAsString, not(containsString("stringValue")));
	}

	@Test
	public void testMyDtoWithoutJsonInclude() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		MyDtoWithoutJsonInclude dtoObject = new MyDtoWithoutJsonInclude();

		String dtoAsString = mapper.writeValueAsString(dtoObject);
		log.debug("dtoAsString: {}", dtoAsString);

		assertThat(dtoAsString, containsString("intValue"));
		assertThat(dtoAsString, containsString("stringValue"));
	}

	@Test
	public void givenNullsIgnoredGlobally_whenWritingObjectWithNullField_thenIgnored()
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); //이건 globally하게 필드를 무시하도록 함
		MyDto dtoObject = new MyDto();

		String dtoAsString = mapper.writeValueAsString(dtoObject);

		assertThat(dtoAsString, containsString("intValue"));
		assertThat(dtoAsString, containsString("booleanValue"));
		assertThat(dtoAsString, not(containsString("stringValue")));
	}

	@Test
	public void test_POJO_TO_MAP() {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap();
		map.put("name", "Frank");
		map.put("id", 5);

		//Map -> POJO
		Person person = mapper.convertValue(map, Person.class);
		log.info("person: {}", person);

		//POJO -> Map
		Map<String, Object> pojoToMap = mapper.convertValue(person, new TypeReference<Map<String, Object>>() {});
		log.info("pojoToMap: {}", pojoToMap);

	}
}
