package kr.pe.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Verifier;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class VerifierRuleTest {
	int MAX_AGE = 25;
	List<Person> peopleWithAgeGreaterThanMaxAge = new ArrayList<>();

	@Rule
	public Verifier verifier = new Verifier() {
		@Override public void verify() {
			assertThat(peopleWithAgeGreaterThanMaxAge.size()).as("나이 %d가 넘는 사람: %s", MAX_AGE, peopleWithAgeGreaterThanMaxAge).isEqualTo(0);
		}
	};

	@Test
	public void personTest1() {
		Person person = Person.builder()
				.name("Frank")
				.age(20)
				.build();

		if (person.getAge() > MAX_AGE) {
			peopleWithAgeGreaterThanMaxAge.add(person);
		}
	}

	@Test
	public void personTest2() {
		Person person = Person.builder()
				.name("Angela")
				.age(30)
				.build();

		if (person.getAge() > MAX_AGE) {
			peopleWithAgeGreaterThanMaxAge.add(person);
		}
	}
}
