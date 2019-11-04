package com.advenoh.controller;

import kr.pe.advenoh.model.Address;
import kr.pe.advenoh.model.Employee;
import kr.pe.advenoh.util.ApiCallResponseExtractor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {
	final String BASE_URL = "http://localhost:8080";

	RestTemplate restTemplate = new RestTemplate();

	//GET
	@Test
	public void test_getForObject() {
		Employee employee = restTemplate.getForObject(BASE_URL + "/employee/{id}", Employee.class, 25);
		log.info("employee: {}", employee);
	}

	@Test
	public void test_getForEntity() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(BASE_URL + "/employee/{id}", String.class, 25);
		log.info("statusCode: {}", responseEntity.getStatusCode());
		log.info("getBody: {}", responseEntity.getBody());
	}

	@Test
	public void test_getForEntity_여러_path_variables을_넘겨주는_경우() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("name", "Frank Oh");
		params.add("country", "US");

		ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(BASE_URL + "/employee/{name}/{country}", Employee.class, params);
		log.info("statusCode: {}", responseEntity.getStatusCode());
		log.info("getBody: {}", responseEntity.getBody());
	}

	//POST
	@Test
	public void testPostForObject_해더_포함해서_보내지_않기() {
		Employee newEmployee = Employee.builder()
				.name("Frank")
				.address(Address.builder()
						.country("US")
						.build())
				.build();

		Employee employee = restTemplate.postForObject(BASE_URL + "/employee", newEmployee, Employee.class);
		log.info("employee: {}", employee);
	}

	@Test
	public void testPostForObject_해데_포함해서_보내기() {
		Employee newEmployee = Employee.builder()
				.name("Frank")
				.address(Address.builder()
						.country("US")
						.build())
				.build();

		HttpHeaders headers = new HttpHeaders();
		headers.set("headerTest", "headerValue");

		HttpEntity<Employee> request = new HttpEntity<>(newEmployee, headers);

		Employee employee = restTemplate.postForObject(BASE_URL + "/employee", request, Employee.class);
		log.info("employee: {}", employee);
	}

	@Test
	public void testPostForEntity_스트링값으로_받기() {
		Employee newEmployee = Employee.builder()
				.name("Frank")
				.address(Address.builder()
						.country("US")
						.build())
				.build();

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(BASE_URL + "/employee", newEmployee, String.class);
		log.info("statusCode: {}", responseEntity.getStatusCode());
		log.info("getBody: {}", responseEntity.getBody());
	}

	@Test
	public void testPostForLocation() {
		Employee newEmployee = Employee.builder()
				.name("Frank")
				.address(Address.builder()
						.country("US")
						.build())
				.build();

		HttpEntity<Employee> request = new HttpEntity<>(newEmployee);

		URI location = restTemplate.postForLocation(BASE_URL + "/employee/location", request);
		log.info("location: {}", location);
	}

	//DELETE
	@Test
	public void test_Delete() {
		Map<String, String> params = new HashMap<>();
		params.put("name", "Frank");
		restTemplate.delete(BASE_URL + "/employee/{name}", params);
	}

	//PUT
	@Test
	public void test_Put() {
		Map<String, String> params = new HashMap<>();
		params.put("name", "Frank");
		Address address = Address.builder()
				.city("Columbus")
				.country("US")
				.build();
		restTemplate.put(BASE_URL + "/employee/{name}", address, params);
	}

	@Test
	public void test_exchange() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("Hello World!", headers);
		log.info("request: {}", request);

		ResponseEntity<Employee> empEntity = restTemplate.exchange(BASE_URL + "/exchange/employee/{id}", HttpMethod.GET, request, Employee.class, 50);
		log.info("empEntity: {}", empEntity);
	}

	@Test
	public void test_get_lists_of_objects() {
		ResponseEntity<List<Employee>> responseEntity = restTemplate
				.exchange(BASE_URL + "/employees", HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
				});
		log.info("responseEntity: {}", responseEntity);
	}

	@Test
	public void test_optionsForAllow() {
		final Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(BASE_URL + "/employee");
		log.info("optionsForAllow: {}", optionsForAllow);
	}

	@Test
	public void test_timeout() {
		final ClientHttpRequestFactory requestFactory = getRequestFactory();
		final RestTemplate restTemplateTimeout = new RestTemplate(requestFactory);

		assertThatThrownBy(() -> restTemplateTimeout.getForObject(BASE_URL + "/timeout/{id}", Employee.class, 25))
				.isInstanceOf(ResourceAccessException.class);
	}

	//PATCH
	@Test
	public void test_patchForObject() {
		final RestTemplate patchRestTemplate = new RestTemplate(getRequestFactory());

		Address address = Address.builder()
				.city("Columbus")
				.country("US")
				.build();

		patchRestTemplate.patchForObject(BASE_URL + "/employee/{name}", address, Address.class, "frank");
	}

	@Test
	public void testExecute() {
		Address address = Address.builder()
				.city("Columbus")
				.country("US")
				.build();
		restTemplate.execute(BASE_URL + "/employee/{name}", HttpMethod.PUT, requestCallback(address), clientHttpResponse -> null, "frank");
	}

	@Test
	public void test() {
		ResponseExtractor<String> responseExtractor = new ApiCallResponseExtractor(String.class, restTemplate.getMessageConverters());

		String response = restTemplate.execute(BASE_URL + "/error", HttpMethod.GET, null, responseExtractor);
		log.info("response :{}", response);
	}

	RequestCallback requestCallback(final Address address) {
		return clientHttpRequest -> {
			log.info("address : {}", address);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(clientHttpRequest.getBody(), address);
			clientHttpRequest.getHeaders().add(
					HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			clientHttpRequest.getHeaders().add(
					HttpHeaders.AUTHORIZATION, "Basic " + "testpasswd");
		};
	}

	/**
	 * connection timeout : 5초
	 * read timeout : 5초
	 *
	 * @return
	 */
	ClientHttpRequestFactory getRequestFactory() {
		final int timeoutInSecs = 5;

		final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(timeoutInSecs * 1000);
		clientHttpRequestFactory.setReadTimeout(timeoutInSecs * 1000);
		return clientHttpRequestFactory;
	}
}