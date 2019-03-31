package com.advenoh.controller;

import com.advenoh.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {
	final String BASE_URL = "http://localhost:8080/employee";

	RestTemplate restTemplate = new RestTemplate();

	/**
	 * getEmployee() : 주어진 URL 주소로 HTTP GET 메서드로 객체로 결과를 반환받는다.
	 * getForEntity() : 주어진 URL 주소로 HTTP GET 메서드로 결과는 ResponseEntity로 반환받는다.
	 * exchange() : HTTP 헤더를 새로 만들 수 있고 어떤 HTTP 메서드도 사용가능하다.
	 * postForObject() : POST 요청을 보내고 객체로 결과를 반환받는다.
	 * postForEntity() : POST 요청을 보내고 결과로 ResponseEntity로 반환받는다
	 * postForLocation() : POST 요청을 보내고 결과로 헤더에 저장된 URI를 결과로 반환받는다
	 * headForHeaders() : 헤더의 모든 정보를 얻을 수 있으면 HTTP HEAD 메서드를 사용한다.
	 * delete() : 주어진 URL 주소로 HTTP DELETE 메서드를 실행한다.
	 * put() : 주어진 URL 주소로 HTTP PUT 메서드를 실행한다.
	 * execute():
	 */
	@Test
	public void test_getForObject() {
		Employee employee = restTemplate.getForObject(BASE_URL + "/{id}", Employee.class, 25);
		log.info("employee: {}", employee);
	}

	@Test
	public void test_getForEntity() {
		ResponseEntity<Employee> empEntity = restTemplate.getForEntity(BASE_URL + "/{id}", Employee.class, 25);
		log.info("statusCode: {}", empEntity.getStatusCode());
		log.info("getBody: {}", empEntity.getBody());
	}

	@Test
	public void test_getForEntity_여러_path_variables을_넘겨주는_경우() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("name", "Frank Oh");
		params.add("country", "US");

		ResponseEntity<Employee> empEntity = restTemplate.getForEntity(BASE_URL + "/{name}/{country}", Employee.class, params);
		log.info("statusCode: {}", empEntity.getStatusCode());
		log.info("getBody: {}", empEntity.getBody());
	}

	@Test
	public void test_get_lists_of_objects() {
		//https://www.baeldung.com/spring-rest-template-list
	}

	@Test
	public void test_exchange() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>("Hello World!", headers);
		log.info("entity: {}", entity);

		ResponseEntity<Employee> empEntity = restTemplate.exchange(BASE_URL + "/exchange/{id}", HttpMethod.GET, entity, Employee.class, 50);
		log.info("empEntity: {}", empEntity);
	}

	//	@Test
	//	public void testExchange() {
	//		String uri = "http://localhost:8080/data/exchange/{id}";
	//		HttpHeaders headers = new HttpHeaders();
	//		headers.setContentType(MediaType.APPLICATION_JSON);
	//		HttpEntity<String> entity = new HttpEntity<>("Hello World!", headers);
	//		log.info("entity: {}", entity);
	//
	//		ResponseEntity<Person> personEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, Person.class, 100);
	//		System.out.println("ID:" + personEntity.getBody().getId());
	//		System.out.println("Name:" + personEntity.getBody().getName());
	//		System.out.println("Village:" + personEntity.getBody().getAddress().getVillage());
	//	}
	//
	//	@Test
	//	public void testHeadForHeaders() {
	//		String url = "http://localhost:8080/data/fetch/{id}";
	//		HttpHeaders httpHeaders = restTemplate.headForHeaders(url, 100);
	//		System.out.println(httpHeaders.getDate());
	//		System.out.println(httpHeaders.getContentType());
	//	}
	//
	//
	//	@Test
	//	public void testPostForObject() {
	//		String url = "http://localhost:8080/data/saveinfo/{id}/{name}";
	//		Map<String, String> map = new HashMap<>();
	//		map.put("id", "111");
	//		map.put("name", "Shyam");
	//		Address address = new Address("Dhananjaypur", "Varanasi", "UP");
	//		Person person = restTemplate.postForObject(url, address, Person.class, map);
	//		System.out.println(person.getName());
	//		System.out.println(person.getAddress().getVillage());
	//	}
	//
	//	@Test
	//	public void testPostForEntity() {
	//		String url = "http://localhost:8080/data/saveinfo/{id}/{name}";
	//		Map<String, String> map = new HashMap<>();
	//		map.put("id", "111");
	//		map.put("name", "Shyam");
	//		Address address = new Address("Dhananjaypur", "Varanasi", "UP");
	//		ResponseEntity<Person> entity = restTemplate.postForEntity(url, address, Person.class, map);
	//		System.out.println(entity.getBody().getName());
	//		System.out.println(entity.getBody().getAddress().getVillage());
	//	}
	//
	//	@Test
	//	public void testPostForLocation() {
	//		String url = "http://localhost:8080/data/location/{id}/{name}";
	//		Address address = new Address("Dhananjaypur", "Varanasi", "UP");
	//		URI uri = restTemplate.postForLocation(url, address, 111, "Shyam");
	//		log.info("uri: {}", uri);
	//		System.out.println(uri.getPath());
	//	}
	//
	//	@Test
	//	public void testDelete() {
	//		String url = "http://localhost:8080/data/delete/{name}/{village}";
	//		Map<String, String> map = new HashMap<>();
	//		map.put("name", "Mahesh");
	//		map.put("village", "Dhananjaypur");
	//		restTemplate.delete(url, map);
	//	}
	//
	//	@Test
	//	public void testPut() {
	//		String url = "http://localhost:8080/data/putdata/{id}/{name}";
	//		Map<String, String> map = new HashMap<String, String>();
	//		map.put("id", "100");
	//		map.put("name", "Ram");
	//		Address address = new Address("Dhananjaypur", "Varanasi", "UP");
	//		restTemplate.put(url, address, map);
	//	}
}