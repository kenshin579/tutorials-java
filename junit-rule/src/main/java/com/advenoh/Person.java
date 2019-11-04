package com.advenoh;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
	String name;
	int age;
	String address;
	String email;
}
