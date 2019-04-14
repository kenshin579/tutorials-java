package com.advenoh.controller;

import com.advenoh.model.Address;
import com.advenoh.model.Employee;
import com.advenoh.model.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class EmployeeController {

	@RequestMapping(method = RequestMethod.GET, value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployee(@PathVariable Long id) {
		log.info("id: {}", id);

		Employee employee = Employee.builder()
				.id(id)
				.name("Frank Oh")
				.gender(Gender.FEMALE)
				.address(Address.builder()
						.street("123동 135호 파란아마트")
						.city("Seoul")
						.postalCode("1234")
						.build())
				.build();
		log.info("employee: {}", employee);
		return employee;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employee/{name}/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployeeByNameAndCountry(@PathVariable String name, @PathVariable String country) {
		log.info("name: {} country: {}", name, country);

		Employee employee = Employee.builder()
				.name(name)
				.address(Address.builder()
						.country(country)
						.build())
				.build();

		log.info("employee: {}", employee);
		return employee;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployees() {
		List<Employee> lists = new ArrayList();

		lists.add(Employee.builder()
				.name("frank1")
				.address(Address.builder()
						.country("US")
						.build())
				.build());

		lists.add(Employee.builder()
				.name("frank2")
				.address(Address.builder()
						.country("US")
						.build())
				.build());

		log.info("lists: {}", lists);
		return lists;
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		log.info("employee: {}", employee);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	//	@RequestMapping(value = "/location/{id}/{name}", method = RequestMethod.POST)
	//	public ResponseEntity<Void> locationURI(@PathVariable(value = "id") Integer id,
	//			@PathVariable(value = "name") String name, @RequestBody Address address,
	//			UriComponentsBuilder builder) {
	//		System.out.println("Id:" + id + " Name:" + name);
	//		System.out.println("Village:" + address.getVillage() + " District:" + address.getDistrict());
	//		HttpHeaders headers = new HttpHeaders();
	//		headers.setLocation(builder.path("/location/{id}/{name}").buildAndExpand(id, name).toUri());
	//		log.info("headers: {}", headers);
	//		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	//	}

	@RequestMapping(method = RequestMethod.GET, value = "/exchange/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeByExchangeMethod(@PathVariable Long id, @RequestBody String body, @RequestHeader HttpHeaders headers) {
		log.info("body: {} headers: {}", body, headers);
		Employee employee = Employee.builder()
				.name("Frank")
				.address(Address.builder()
						.country("US")
						.build())
				.build();

		log.info("employee: {}", employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	//	@RequestMapping(value = "/exchange/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	//	public ResponseEntity<Employee> exchangeData(@PathVariable(value = "id") Integer id) {
	//		Address address = new Address("Dhananjaypur", "Varanasi", "UP");
	//		Employee Employee = new Employee(id, "Mahesh", address);
	//		return new ResponseEntity<Employee>(Employee, HttpStatus.OK);
	//	}
	//
	//	@RequestMapping(value = "/delete/{name}/{village}", method = RequestMethod.DELETE)
	//	public void deleteData(@PathVariable(value = "name") String name,
	//			@PathVariable(value = "village") String village) {
	//		System.out.println("Delete Employee with name:" + name + " and village:" + village);
	//	}
	//
	//	@RequestMapping(value = "/fetch/{id}", method = RequestMethod.HEAD)
	//	public ResponseEntity<Void> fetch(@PathVariable(value = "id") Integer id) {
	//		System.out.println("Id:" + id);
	//		HttpHeaders headers = new HttpHeaders();
	//		headers.setContentType(MediaType.APPLICATION_JSON);
	//		return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
	//	}
	//
	//	@RequestMapping(value = "/putdata/{id}/{name}", method = RequestMethod.PUT)
	//	public void putData(@PathVariable(value = "id") String id,
	//			@PathVariable(value = "name") String name, @RequestBody Address address) {
	//		System.out.println("Id:" + id + " Name:" + name);
	//		System.out.println("District:" + address.getDistrict());
	//		System.out.println("Village:" + address.getVillage());
	//	}

}
