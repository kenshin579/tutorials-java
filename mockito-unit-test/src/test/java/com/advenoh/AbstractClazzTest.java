package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * https://blogs.agilefaqs.com/2013/12/05/mocking-only-abstract-methods-using-mockito-partial-mocking/
 */
@Slf4j
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

	/**
	 * abstract에 있는 부모 메서드 mock하는 방법
	 */
	@Test
	public void mocking_parent_inner_method_from_child_class() {
		ChildClazz3 childClazz3 = spy(ChildClazz3.class);
		doReturn("angela").when(childClazz3).getParentName();
		assertThat(childClazz3.testMethod()).isEqualTo("angela");
	}
}