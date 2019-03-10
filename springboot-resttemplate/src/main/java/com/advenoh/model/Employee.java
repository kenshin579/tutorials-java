package com.advenoh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	@JsonIgnore
	private Long id;
	private String name;

	private Integer age;
	Gender gender;

	private Float salary;

	Address address;
}
