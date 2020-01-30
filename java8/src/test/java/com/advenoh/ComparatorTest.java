package com.advenoh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class ComparatorTest {
	List<Student> students;

	@Before
	public void setUp() throws Exception {
		students = new ArrayList<>();
		Student student;

		for (int i = 0; i < 10; i++) {
			student = Student.builder()
					.name("name" + i)
					.age(i)
					.build();
			students.add(student);
		}

		students.get(3).setAge(0);
		students.get(5).setAge(0);
	}

	/**
	 * https://www.javatpoint.com/Comparator-interface-in-collection-framework
	 * https://stackoverflow.com/questions/15389187/how-to-always-have-empty-values-in-the-end-of-a-sorting/15389332
	 * https://codereview.stackexchange.com/questions/93082/rearranging-array-with-empty-strings-in-front-and-non-empty-strings-at-back
	 * https://stackoverflow.com/questions/30105430/sorting-half-empty-array-in-java
	 *
	 * <p>
	 * age가 0이면 맨 뒤로 옮기기
	 */
	@Test
	public void test_comparator() throws JsonProcessingException {
		Collections.sort(students, (o1, o2) -> {
			if (o1.getAge() == 0 && o2.getAge() == 0)
				return 0;
			if (o1.getAge() == 0) return 1;
			if (o2.getAge() == 0) return -1;
			return 0;
		});

		log.info("students: {}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(students));
	}

	@Test
	public void test_stream_sort() throws JsonProcessingException {
		Student[] students = this.students.stream().sorted(Comparator.comparing(x -> x.getAge() == 0 ? 1 : 0)).toArray(x -> new Student[x]);
		log.info("{}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(students));
	}
}
