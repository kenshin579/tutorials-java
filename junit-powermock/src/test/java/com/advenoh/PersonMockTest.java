package kr.pe.advenoh;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class PersonMockTest {

	@Test
	public void name() {
		Child p = mock(Child.class);
		Assertions.assertThat(p).isNotNull();
	}
}
