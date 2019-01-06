package com.concretepage.controller;

import com.concretepage.entity.Address;
import com.concretepage.entity.Company;
import com.concretepage.entity.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/data")
public class PersonController {
	@RequestMapping(value = "/fetchjson/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person getForObjectJsonDemo(@PathVariable(value = "id") Integer id) {
		Address address = new Address("Dhananjaypur", "Varanasi", "UP");
		return new Person(id, "Ram", address);
	}

	@RequestMapping(value = "/fetchxml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public Company getForObjectXMLDemo(@PathVariable(value = "id") Integer id) {
		Company comp = new Company();
		comp.setId(id);
		comp.setCompanyName("XYZ");
		comp.setCeoName("ABCD");
		comp.setNoEmp(100);
		return comp;
	}

	@RequestMapping("/fetch/{name}/{village}")
	public Person getPersonDetail(@PathVariable(value = "name") String name,
			@PathVariable(value = "village") String village) {
		Address address = new Address(village, "Varanasi", "UP");
		return new Person(100, name, address);
	}

	@RequestMapping(value = "/exchange/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> exchangeData(@PathVariable(value = "id") Integer id) {
		Address address = new Address("Dhananjaypur", "Varanasi", "UP");
		Person person = new Person(id, "Mahesh", address);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{name}/{village}", method = RequestMethod.DELETE)
	public void deleteData(@PathVariable(value = "name") String name,
			@PathVariable(value = "village") String village) {
		System.out.println("Delete person with name:" + name + " and village:" + village);
	}

	@RequestMapping(value = "/fetch/{id}", method = RequestMethod.HEAD)
	public ResponseEntity<Void> fetch(@PathVariable(value = "id") Integer id) {
		System.out.println("Id:" + id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/putdata/{id}/{name}", method = RequestMethod.PUT)
	public void putData(@PathVariable(value = "id") String id,
			@PathVariable(value = "name") String name, @RequestBody Address address) {
		System.out.println("Id:" + id + " Name:" + name);
		System.out.println("District:" + address.getDistrict());
		System.out.println("Village:" + address.getVillage());
	}

	@RequestMapping(value = "/saveinfo/{id}/{name}", method = RequestMethod.POST)
	public ResponseEntity<Person> saveInfo(@PathVariable(value = "id") Integer id,
			@PathVariable(value = "name") String name, @RequestBody Address address) {
		Person person = new Person(id, name, address);
		return new ResponseEntity<Person>(person, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/location/{id}/{name}", method = RequestMethod.POST)
	public ResponseEntity<Void> locationURI(@PathVariable(value = "id") Integer id,
			@PathVariable(value = "name") String name, @RequestBody Address address,
			UriComponentsBuilder builder) {
		System.out.println("Id:" + id + " Name:" + name);
		System.out.println("Village:" + address.getVillage() + " District:" + address.getDistrict());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/location/{id}/{name}").buildAndExpand(id, name).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}