package com.advenoh;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import static org.mockito.ArgumentMatchers.anyString;

public class MyAbstractClassTestUsingSpy {

	private MyAbstractClass myAbstractClass;
	private String testStr = "test123";

	@Before
	public void setup() {

		//Use Spy to make an object of the MyAbstractClass instead of instantiating
		myAbstractClass = Mockito.spy(MyAbstractClass.class);
	}

	@Test
	public void should_operate_doWork_method_successfully_when_default_id_and_overridden_id_are_not_equal_and_doWorkInForcefulWay_method_return_not_null() {
		Mockito.when(myAbstractClass.doWorkInForcefulWay(anyString())).thenReturn(testStr);

		Whitebox.setInternalState(myAbstractClass, "defaultId", "123");

		Assert.assertEquals(testStr, myAbstractClass.doWork("456"));
	}

	@Test
	public void should_operate_doWork_method_successfully_when_default_id_and_overridden_id_are_not_equal_and_doWorkInForcefulWay_method_return_null() {
		Mockito.when(myAbstractClass.doWorkInForcefulWay(anyString())).thenReturn(null);
		Mockito.when(myAbstractClass.doWorkInDefaultWay(anyString())).thenReturn(testStr);

		Whitebox.setInternalState(myAbstractClass, "defaultId", "123");
		Whitebox.setInternalState(myAbstractClass, "doFallBack", true);

		Assert.assertEquals(testStr, myAbstractClass.doWork(anyString()));
	}
}