package kr.jdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	private String name;
	private int age;

	public List<Person> getList(String name, int age) {
		List<Person> list = new ArrayList<>();
		list.add(new Person(name, age));
		return list;
	}
}
