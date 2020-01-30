package com.advenoh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ComparatorTest {
	/**
	 * https://www.javatpoint.com/Comparator-interface-in-collection-framework
	 *
	 */
	@Test
	public void test_comparator() throws JsonProcessingException {
		List<Student> students = new ArrayList<>();
		Student student;

		for (int i = 0; i < 3; i++) {
			student = Student.builder()
					.name("name" + i)
					.age(i)
					.build();
			students.add(student);
		}

		Collections.sort(students, (o1, o2) -> {
			if (o1.getAge() < o2.getAge())
				return 5;
			else if (o1.getAge() > o2.getAge())
				return -1;
			else
				return 0;
		});

		log.info("students: {}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(students));
	}
}
