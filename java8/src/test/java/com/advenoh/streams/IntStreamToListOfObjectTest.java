package com.advenoh.streams;

import com.advenoh.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class IntStreamToListOfObjectTest {
	private int MAX = 3;

	@Test
	public void generate_list_of_obj_with_for() {
		List<Student> students = new ArrayList<>();
		for (int i = 0; i < MAX; i++) {
			students.add(new Student("name" + i, i + 10));
		}
		assertThat(students.size()).isEqualTo(MAX);

	}

	/**
	 * https://stackoverflow.com/questions/22649978/java-8-lambda-can-i-generate-a-new-arraylist-of-objects-from-an-intstream
	 * http://jtuts.com/2017/04/21/create-list-range-integers-using-java-8/
	 */
	@Test
	public void convert_intstream_list_of_obj() {
		List<Student> students = IntStream.range(0, MAX)
				.mapToObj(i -> new Student("name" + i, i + 10))
				.collect(Collectors.toList());

		assertThat(students.size()).isEqualTo(MAX);
	}

	@Test
	public void generateListOfIntegers() {
		List<Integer> intList = IntStream.range(0, MAX).boxed().collect(Collectors.toList());
		assertThat(intList.size()).isEqualTo(MAX);
	}
}
