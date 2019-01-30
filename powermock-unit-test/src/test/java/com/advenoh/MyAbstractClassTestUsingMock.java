package com.advenoh;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import static org.mockito.ArgumentMatchers.anyString;

public class MyAbstractClassTestUsingMock {

	private MyAbstractClass myAbstractClass;
	private String testStr = "test123";

	@Before
	public void setup() {
		//Instead of spying, make a mock object of MyAbstractClass
		myAbstractClass = Mockito.mock(MyAbstractClass.class);
	}

	@Test
	public void should_operate_doWork_method_successfully_when_default_id_and_overridden_id_are_not_equal_and_doWorkInForcefulWay_method_return_not_null() {
		Mockito.when(myAbstractClass.doWorkInForcefulWay(Matchers.anyString())).thenReturn(testStr);

		Whitebox.setInternalState(myAbstractClass, "defaultId", "123");

		//To trigger the real method invokation, call doCallRealMethod method
		Mockito.doCallRealMethod().when(myAbstractClass).doWork("456");
		Assert.assertEquals(testStr, myAbstractClass.doWork("456"));
	}

	@Test
	public void should_operate_doWork_method_successfully_when_default_id_and_overridden_id_are_not_equal_and_doWorkInForcefulWay_method_return_null() {
		Mockito.when(myAbstractClass.doWorkInForcefulWay(anyString())).thenReturn(null);
		Mockito.when(myAbstractClass.doWorkInDefaultWay(anyString())).thenReturn(testStr);

		Whitebox.setInternalState(myAbstractClass, "defaultId", "123");
		Whitebox.setInternalState(myAbstractClass, "doFallBack", true);

		//To trigger the real method invokation, call doCallRealMethod method
		Mockito.doCallRealMethod().when(myAbstractClass).doWork(Matchers.anyString());
		Assert.assertEquals(testStr, myAbstractClass.doWork(Matchers.anyString()));
	}
}