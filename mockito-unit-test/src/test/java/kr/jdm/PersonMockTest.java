package kr.jdm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
public class PersonMockTest {

	@Mock
	Person mockPerson;

	@Test
	public void withoutAnnotation() {
		Person p = mock(Person.class);
		assertThat(p).isNotNull();

		when(p.getName()).thenReturn("Frank");
		assertThat(p.getName()).isEqualTo("Frank");
	}

	@Test
	public void withAnnotation() {
		MockitoAnnotations.initMocks(this);
		assertThat(mockPerson).isNotNull();
	}

	@Test
	public void example_thenReturn() {
		MockitoAnnotations.initMocks(this);
		when(mockPerson.getList(anyString(), anyInt())).thenReturn(new ArrayList<Person>() {
			{
				this.add(new Person("Angela", 45));
			}
		});

		List<Person> list = mockPerson.getList("Frank", 40);
		assertThat(list.get(0).getName()).isEqualTo("Angela");
		assertThat(list.get(0).getAge()).isEqualTo(45);

		verify(mockPerson).getList("Frank", 40);
	}

	@Test
	public void example_doThrow() {
		MockitoAnnotations.initMocks(this);
		doThrow(new IllegalArgumentException("dothrow test")).when(mockPerson).setName(eq("Frank"));
		assertThatThrownBy(() -> mockPerson.setName("Frank")).hasMessageContaining("dothrow test");
	}

	@Test
	public void example_verify() {
		MockitoAnnotations.initMocks(this);
		mockPerson.setName("Frank");

		// n번 호출했는지 체크
		verify(mockPerson, Mockito.times(1)).setName(any(String.class)); // success

		// 호출 안했는지 체크
		verify(mockPerson, never()).getName(); // success
		verify(mockPerson, never()).setName(eq("Angela")); // success

		// 최소한 1번 이상 호출했는지 체크
		verify(mockPerson, atLeastOnce()).setName(any(String.class)); // success

		// 2번 이하 호출 했는지 체크
		verify(mockPerson, atMost(2)).setName(any(String.class)); // success

		// 2번 이상 호출 했는지 체크
		//		verify(mockPerson, atLeast(2)).setName(any(String.class)); // fail

		// 지정된 시간(millis)안으로 메소드를 호출 했는지 체크
		verify(mockPerson, timeout(100)).setName(any(String.class)); // success

		// 지정된 시간(millis)안으로 1번 이상 메소드를 호출 했는지 체크
		verify(mockPerson, timeout(100).atLeast(1)).setName(any(String.class)); // success
	}

	@Mock
	AuthDao authDao;

	//AuthSevice 클래스 내부에 AuthDao 메서드를 사용하고 있을 때 @Mock이나 @Spy로 선언된 목 객체중에 맴버 클래스와 일치하는 게 있으면 주입시켜줌
	@InjectMocks
	AuthService authService;

	@Test
	public void example_injectmocks() {
		MockitoAnnotations.initMocks(this);
		when(authDao.isLogin("kenshin")).thenReturn(true);

		assertThat(authService.isLogin("kenshin")).isTrue();
		assertThat(authService.isLogin("kenshin1234")).isFalse();
	}

	@Test
	public void example_spy() {
		Person person = spy(Person.class);
		when(person.getName()).thenReturn("Frank");

		person.setAge(15);
		assertThat(person.getName()).isEqualTo("Frank");
		assertThat(person.getAge()).isEqualTo(15);
	}

	@Test
	public void example_thenAnswer에_값을_하나_더하는_예제() {
		List mockList = mock(List.class);

		when(mockList.get(anyInt())).thenAnswer((Answer<Integer>) invocationOnMock -> {
			Object[] args = invocationOnMock.getArguments();
			log.info("args: {}", args); //내부적으로 데이터를 manipulate이 가능함
			return (Integer) args[0] + 1;
		});

		assertThat(mockList.get(1)).isEqualTo(2);
	}

	class ListOfTwoElements extends ArgumentMatcher<List> {
		@Override public boolean matches(Object o) {
			return ((List) o).size() == 2;
		}
	}

	//todo: 잘 이해 안됨
	@Test
	public void example_custom_argument_matcher() {
		List mock = mock(List.class);
		when(mock.addAll(argThat(new ListOfTwoElements()))).thenReturn(true);

		mock.addAll(Arrays.asList("one", "two")); // true

		verify(mock).addAll(argThat(new ListOfTwoElements()));
	}

	@Test
	public void examaple_capturing() {
		List mock = mock(List.class);
		mock.addAll(Arrays.asList("one", "two"));  // false

		ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
		verify(mock).addAll(argument.capture());
		assertThat(argument.getValue().size() == 2).isTrue();
	}
}