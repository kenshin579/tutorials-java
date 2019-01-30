package com.advenoh;

import com.advenoh.model.Person;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PersonMockTest {

	@Test
	public void name() {
		Person p = mock(Person.class);
		Assertions.assertThat(p).isNotNull();
	}
}
