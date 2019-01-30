package com.advenoh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
	@Mock
	private List<String> mockList;

	@Test
	public void testMockListAdd() {
		String addString = "some string";
		mockList.add(addString);

		//verify that the add method was called with argument 'some string'
		verify(mockList).add(addString);
	}

	@Test
	public void testMockListAddMultiple() {
		String addString = "some string multiple";
		mockList.add(addString);
		mockList.add(addString);
		mockList.add(addString);

		//verify that the add method was called with argument 'some string'
		verify(mockList, times(3)).add(addString);
	}
}
