package com.java.examples;

import model.Person;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

import static enums.Gender.FEMALE;
import static enums.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;

public class AssertJTest {
	/**
	 * https://mhaligowski.github.io/blog/2014/09/13/new-extracting.html
	 * https://github.com/joel-costigliola/assertj-examples/blob/master/assertions-examples/src/test/java/org/assertj/examples/IterableAssertionsExamples.java
	 */
	@Test
	public void test_extracting() {
		Person wilma = new Person("wilma", FEMALE);
		Person fred = new Person("fred", MALE);

		List<Person> persons = Lists.newArrayList(wilma, fred); // guava-style

		assertThat(persons).extracting("gender").containsExactly(FEMALE, MALE);

	}
}
