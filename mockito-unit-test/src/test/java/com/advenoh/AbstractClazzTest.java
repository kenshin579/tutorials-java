package com.advenoh;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * https://blogs.agilefaqs.com/2013/12/05/mocking-only-abstract-methods-using-mockito-partial-mocking/
 */
public class AbstractClazzTest {
	/**
	 * abstract 클래스에서 메서드 실행이 안됨
	 */
	@Test
	public void shouldCallRealMethods() {
		AbstractClazz clazz = mock(AbstractClazz.class);
		assertThat(clazz.sayHello()).isNull();
	}

	@Test
	public void shouldCallRealMethodsAndFakeAbstractMethod() {
		AbstractClazz clazz = mock(AbstractClazz.class, CALLS_REAL_METHODS);
		when(clazz.fetchName()).thenReturn("Naresh");
		assertThat(clazz.sayHello()).isEqualTo("Hello Naresh!");
	}

	@Test
	public void shouldCallRealMethodsAndFakeAbstractMethod2() {
		AbstractClazz clazz = mock(AbstractClazz.class);
		when(clazz.sayHello()).thenCallRealMethod();
		when(clazz.fetchName()).thenReturn("Naresh");
		assertThat(clazz.sayHello()).isEqualTo("Hello Naresh!");
	}

	@Test
	public void shouldCallRealMethodsAndFakeAbstractMethod3() {
		AbstractClazz2 clazz = mock(AbstractClazz2.class, new AbstractMethodMocker());
		when(clazz.fetchName()).thenReturn("Naresh");
		assertThat(clazz.sayHello()).isEqualTo("Hello Naresh!");
	}
}