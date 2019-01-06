package com.concretepage.controller;

import com.concretepage.entity.Address;
import com.concretepage.entity.Company;
import com.concretepage.entity.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RestTemplateTest {
	RestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
		restTemplate = new RestTemplate();
	}

	@Test
	public void testDelete() {
		String url = "http://localhost:8080/data/delete/{name}/{village}";
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Mahesh");
		map.put("village", "Dhananjaypur");
		restTemplate.delete(url, map);
	}

	@Test
	public void testExchange() {
		String uri = "http://localhost:8080/data/exchange/{id}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
		ResponseEntity<Person> personEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, Person.class, 100);
		System.out.println("ID:" + personEntity.getBody().getId());
		System.out.println("Name:" + personEntity.getBody().getName());
		System.out.println("Village:" + personEntity.getBody().getAddress().getVillage());
	}

	@Test
	public void testGetForEntity() {
		String url = "http://localhost:8080/data/fetch/{name}/{village}";
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Mahesh");
		map.put("village", "Dhananjaypur");
		ResponseEntity<Person> personEntity = restTemplate.getForEntity(url, Person.class, map);
		System.out.println("Name:" + personEntity.getBody().getName());
		System.out.println("Village:" + personEntity.getBody().getAddress().getVillage());
	}

	@Test
	public void testGetForObjectWithJson() {
		Person person = restTemplate.getForObject("http://localhost:8080/data/fetchjson/{id}", Person.class, 200);
		System.out.println("ID: " + person.getId());
		System.out.println("Name: " + person.getName());
		System.out.println("Village Name: " + person.getAddress().getVillage());
	}

	@Test
	public void testGetObjectWithXml() {

		Company company = restTemplate.getForObject("http://localhost:8080/data/fetchxml/{id}", Company.class, 200);
		System.out.println("ID: " + company.getId());
		System.out.println("Company: " + company.getCompanyName());
		System.out.println("CEO: " + company.getCeoName());

	}

	@Test
	public void testHeadForHeaders() {
		String url = "http://localhost:8080/data/fetch/{id}";
		HttpHeaders httpHeaders = restTemplate.headForHeaders(url, 100);
		System.out.println(httpHeaders.getDate());
		System.out.println(httpHeaders.getContentType());
	}

	@Test
	public void testPostForEntity() {
		String url = "http://localhost:8080/data/saveinfo/{id}/{name}";
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "111");
		map.put("name", "Shyam");
		Address address = new Address("Dhananjaypur", "Varanasi", "UP");
		ResponseEntity<Person> entity = restTemplate.postForEntity(url, address, Person.class, map);
		System.out.println(entity.getBody().getName());
		System.out.println(entity.getBody().getAddress().getVillage());
	}

	@Test
	public void testPostForLocation() {
		String url = "http://localhost:8080/data/location/{id}/{name}";
		Address address = new Address("Dhananjaypur", "Varanasi", "UP");
		URI uri = restTemplate.postForLocation(url, address, 111, "Shyam");
		System.out.println(uri.getPath());
	}

	@Test
	public void testPostForObject() {
		String url = "http://localhost:8080/data/saveinfo/{id}/{name}";
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "111");
		map.put("name", "Shyam");
		Address address = new Address("Dhananjaypur", "Varanasi", "UP");
		Person person = restTemplate.postForObject(url, address, Person.class, map);
		System.out.println(person.getName());
		System.out.println(person.getAddress().getVillage());
	}

	@Test
	public void testPut() {
		String url = "http://localhost:8080/data/putdata/{id}/{name}";
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "100");
		map.put("name", "Ram");
		Address address = new Address("Dhananjaypur", "Varanasi", "UP");
		restTemplate.put(url, address, map);
	}
}
