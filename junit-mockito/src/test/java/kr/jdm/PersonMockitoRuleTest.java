package kr.jdm;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PersonMockitoRuleTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	Person mockPerson;

	@Test
	public void example_mockitoRule() {
		when(mockPerson.getName()).thenReturn("Frank");
		assertThat(mockPerson).isNotNull();

		assertThat(mockPerson.getName()).isEqualTo("Frank");
		verify(mockPerson).getName();
	}
}