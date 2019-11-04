package kr.pe.advenoh;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SomeEntityTest {
	private SomeService someService;

	@Before
	public void setUp() throws Exception {
		someService = new SomeService();
	}

	@Test
	public void testReflectionUtils() {
		SomeEntity someEntity = new SomeEntity();
		ReflectionTestUtils.setField(someEntity, "someProperty", "someValue");

		String result = someService.someMethod(someEntity);

		assertThat(result).isEqualTo("someValue");
	}

	@Test
	public void testInnerClassWithSetter() {
		SomeEntityWithSetters someEntity = new SomeEntityWithSetters();
		someEntity.setSomeProperty("someValue");

		String result = someService.someMethod(someEntity);

		assertThat(result).isEqualTo("someValue");
	}

	public class SomeEntityWithSetters extends SomeEntity {
		public void setSomeProperty(String someProperty) {
			this.someProperty = someProperty;
		}
	}

	@Test
	public void testMockito() {
		SomeEntity someEntity = mock(SomeEntity.class);
		when(someEntity.getSomeProperty()).thenReturn("someValue");

		String result = someService.someMethod(someEntity);

		assertThat(result).isEqualTo("someValue");
	}
}
