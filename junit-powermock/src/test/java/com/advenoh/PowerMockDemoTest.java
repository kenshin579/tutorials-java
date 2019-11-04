package kr.pe.advenoh;

import kr.pe.advenoh.impl.ChildImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PowerMockDemoTest {

	private PowerMockDemo powerMockDemo;

	@Before
	public void setUp() {
		powerMockDemo = new PowerMockDemo();
	}

	@Test
	public void testCallPrivateMethod() throws Exception {
		int actual = Whitebox.invokeMethod(powerMockDemo, "privateMethod", 2, 5);
		assertThat(actual).isEqualTo(7);
	}

	@Test
	public void test_abstract_method를_호출함() throws Exception {
		ChildImpl child = new ChildImpl();

		int actual = Whitebox.invokeMethod(child, "anotherMethod", 2);
		assertThat(actual).isEqualTo(2);
	}

	@Test
	public void test_abstract_method를_mock처리하기() {
//		ChildImpl child = new ChildImpl();

//		spy(ChildImpl.class);
////		ParenParent.class
//		spy();
//
//		when()

	}
}