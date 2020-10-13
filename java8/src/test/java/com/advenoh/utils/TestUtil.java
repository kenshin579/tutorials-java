package com.advenoh.utils;

import com.advenoh.model.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class TestUtil {
	private TestUtil() {
	}

	/**
	 * https://stackoverflow.com/questions/22649978/java-8-lambda-can-i-generate-a-new-arraylist-of-objects-from-an-intstream
	 * http://jtuts.com/2017/04/21/create-list-range-integers-using-java-8/
	 */
	public static List<Student> getStudentSample(int max) {
		return IntStream.range(0, max)
				.mapToObj(i -> new Student("name" + i, i + 10))
				.collect(Collectors.toList());
	}
}