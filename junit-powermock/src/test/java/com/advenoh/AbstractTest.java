package kr.pe.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
public class AbstractTest {
	abstract class AbstractBaseClass {
		public String method() {
			return "AbstractBaseClass.method";
		}
	}

	public class ChildClass extends AbstractBaseClass {
		public String start() {
			return "";
		}
	}

	@Test
	public void 부모클래스_public으로_된_메서드를_mock_하는_방법() {
		ChildClass child = mock(ChildClass.class);
		when(child.method()).thenReturn("my mock string");

		assertThat(child.method(), is("my mock string")); // AbstractBaseClass.method
	}

	@Test
	public void 부모클래스에서_protected된_메서드를_mock하는_방법() {
		Child child = new ChildWithDisabledBadMethod();

		child.methodToTest();

		//put your assertions here
	}

	private static class ChildWithDisabledBadMethod extends Child {
		protected void badMethod() {
			log.info("ChildWithDisabledBadMethod:badMethod");
		}
	}
}
