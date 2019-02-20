package com.advenoh;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class MockitoFeatureTest {

    @Test
    public void testSimple() {
        //mock creation
        List<String> mockedList = mock(List.class);

        //using mock object - mock remembers all the method calls
        mockedList.add("one");
        mockedList.clear();

        //verification - check whether the following methods were called
        verify(mockedList).add("one");
        verify(mockedList).clear();

    }

    @Test(expected = RuntimeException.class)
    public void testStubExample() {
        //You can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        System.out.println(mockedList.get(0));

        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        System.out.println(mockedList.contains(1));

        //following throws runtime exception
        System.out.println(mockedList.get(1));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
        //If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
    }

    @Test
    public void testArgumentMatchers() {
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        //stubbing using hamcrest (let's say isValid() returns your own hamcrest matcher):
        //		when(mockedList.contains(argThat(isValid()))).thenReturn("element");

        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        verify(mockedList).get(anyInt());
    }

    @Test
    public void testCustomArgumentMatchers() {
        List mock = mock(List.class);

        when(mock.addAll(argThat(new IsListOfTwoElements()))).thenReturn(true);

        mock.addAll(Arrays.asList("one", "two"));

        verify(mock).addAll(argThat(new IsListOfTwoElements()));

    }

    class IsListOfTwoElements extends ArgumentMatcher<List> {
        public boolean matches(Object list) {
            return ((List) list).size() == 2;
        }
    }

    @Test
    public void testVerifyingExactNumberOfInvocation() {
        List mockedList = mock(List.class);

        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        //		verify(mockedList, atLeast(2)).add("five times");
        verify(mockedList, atMost(5)).add("three times");
    }

    @Test(expected = RuntimeException.class)
    public void testStubbingVoidMethodsWithException() {
        List mockedList = mock(List.class);

        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        mockedList.clear();
    }

    @Test
    public void testVerificationInOrder() {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        inOrder = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrder.verify(firstMock).add("was called first");
        inOrder.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will
    }

    @Test
    public void testInteractionNeverHappenedOnMock() {
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);

        //using mocks - only mockOne is interacted
        mockOne.add("one");

        //ordinary verification
        verify(mockOne).add("one");

        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        //verify that other mocks were not interacted
        verifyZeroInteractions(mockTwo, mockThree);
    }

    @Test
    public void testFindingRedundantInvocations() {
        List mockedList = mock(List.class);

        //using mocks
        mockedList.add("one");
        //		mockedList.add("two");

        verify(mockedList).add("one");

        //following verification will fail
        verifyNoMoreInteractions(mockedList);
    }

    @Test(expected = RuntimeException.class)
    public void testStubbingConsecutiveCalls() {
        Animal animalMock = mock(Animal.class);

        when(animalMock.someMethod("some arg"))
                .thenThrow(new RuntimeException())
                .thenReturn("foo");

        //First call: throws runtime exception:
        animalMock.someMethod("some arg");

        //Second call: prints "foo"
        System.out.println(animalMock.someMethod("some arg"));

        //Any consecutive call: prints "foo" as well (last stubbing wins).
        System.out.println(animalMock.someMethod("some arg"));

    }

    class Person {
        String name;
        Animal pet;

        public void setPetInfo(Animal animal) {
            this.pet = animal;
        }

    }

    class Animal {
        String petName;

        public Animal(String name) {
            this.petName = name;
        }

        public Object someMethod(String string) {
            return null;
        }

        public String toString() {
            return petName;
        }

        public String getName() {
            return petName;
        }
    }

    @Test
    //	@Ignore(value="Wrong output...")
    public void testStubbingWithCallBacks() {
        Animal animalMock = mock(Animal.class);

        when(animalMock.someMethod(anyString())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "called with arguments: " + args;
            }
        });

        //Following prints "called with arguments: foo"
        System.out.println(animalMock.someMethod("foo"));

    }

    @Test
    public void testSpyRealObject() {
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");

    }


    @Test
    public void testSpyRealObjectDoReturn() {
        List list = new LinkedList();
        List spy = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        //		when(spy.get(0)).thenReturn("foo");

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

    }

    @Test
    public void testCaptureTestArguments() {
        Person personMock = mock(Person.class);

        personMock.setPetInfo(new Animal("Cat"));

        ArgumentCaptor<Animal> argument = ArgumentCaptor.forClass(Animal.class);
        verify(personMock).setPetInfo(argument.capture());
        assertEquals("Cat", argument.getValue().getName());
    }

    @Test
    public void testRealPartialMocks() {
        List list = spy(new LinkedList());

        Animal animalMock = mock(Animal.class);

        animalMock.someMethod("test");
        when(animalMock.someMethod("test")).thenCallRealMethod();

//		verify(animalMock).someMethod("test");

    }
}



