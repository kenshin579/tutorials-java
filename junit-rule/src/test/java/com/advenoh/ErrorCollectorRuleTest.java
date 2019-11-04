package kr.pe.advenoh;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;

public class ErrorCollectorRuleTest {
	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Test
	public void test_첫번째_테스트실행이후에도_실행됨() {
		collector.addError(new Throwable("첫번째 오류!"));
		collector.addError(new Throwable("두번째 오류!"));

		Person person = Person
				.builder()
				.name("Frank")
				.email("asdf@sdf.com")
				.age(25)
				.build();

		collector.checkThat(person.getAge(), is(30)); //실패1
		collector.checkThat(person.getName(), is("Frank")); //성공
		collector.checkThat(person.getEmail(), is("ser@#test.com")); //실패2
	}
}
