package com.java.examples;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class JsonPathTest {

	InputStream jsonStream;

	@Before
	public void setUp() throws Exception {
		jsonStream = getResourceAsStream("sample.json");
	}

	@Test
	public void test_id값으로_데이터를_가져오기() {
		String searchId = "5c2c3278acd492387a5223d7";
		List<Object> lists = JsonPath.parse(jsonStream).read("$[?(@._id == '" + searchId + "')]");
		assertEquals(1, lists.size());
		assertTrue(lists.get(0).toString().contains("Hello, Louella! You have 8 unread messages."));
	}

	@Test
	public void test_id값으로_데이터를_가져오기_using_filter_api() {
		String searchId = "5c2c3278acd492387a5223d7";
		Filter idEqualSearchIdFilter = filter(where("_id").is(searchId));
		Object dataObject = JsonPath.parse(jsonStream).read("$[?]", idEqualSearchIdFilter);
		assertTrue(dataObject.toString().contains("Hello, Louella! You have 8 unread messages."));
	}

	@Test
	public void test_tags가_있는_사람은_모두() {
		List<Map<String, Object>> dataList = JsonPath.parse(jsonStream).read("$[?('pariatur' in @['tags'])]");
		assertTrue(dataList.get(0).get("name").toString().contains("Dawn") && dataList.get(0).get("name").toString().contains("Roach"));
		assertTrue(dataList.get(1).get("name").toString().contains("Deloris") && dataList.get(1).get("name").toString().contains("Albert"));
	}

	@Test
	public void test_모든_사람의_총_계좌_잔고을_계산하기() throws ParseException {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

		DocumentContext documentContext = JsonPath.parse(jsonStream);
		int maxSize = documentContext.read("$.length()");
		double totalAmount = 0.0;

		for (int i = 0; i < maxSize; i++) {
			totalAmount += formatter.parse(documentContext.read("$[" + i + "]['balance']")).doubleValue();
		}
		assertEquals(15998, (int) totalAmount);
	}

	@Test
	public void test_제일_어린_사람을_찾기() {
		DocumentContext documentContext = JsonPath.parse(jsonStream);
		List<Integer> ageList = documentContext.read("$[*]['age']");
		int minAge = ageList.get(ageList.indexOf(Collections.min(ageList)));
		List<Object> lists = documentContext.read("$[?(@['age'] == " + minAge + ")]");
		assertTrue(lists.get(0).toString().contains("Deloris") && lists.get(0).toString().contains("Albert"));
	}

	@Test
	public void test_Person객체로_매핑하기() {
		DocumentContext documentContext = JsonPath.parse(this.getResourceAsStream("person.json"));
		Person person = documentContext.read("$", Person.class);
		assertEquals("Frank Oh", person.getName());
		assertEquals(26, person.getAge());
	}

	@Test
	public void test_jsonpath_함수() {
		DocumentContext documentContext = JsonPath.parse(jsonStream);
		double rangeAvg = documentContext.read("$[0].range.avg()");
		assertEquals(4.5, rangeAvg, 0);
	}

	private InputStream getResourceAsStream(String resourceName) {
		return getClass().getClassLoader().getResourceAsStream(resourceName);
	}
}
