package com.advenoh.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {
	private String name;
	private int age;

	@Builder
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
