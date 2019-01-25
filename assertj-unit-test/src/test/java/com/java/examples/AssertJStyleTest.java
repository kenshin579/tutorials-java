package com.java.examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AssertJStyleTest {

	@Test
	public void testAssertArrayEquals() {
		byte[] expected = "trial".getBytes();
		byte[] actual = "trial".getBytes();
		assertThat(actual).as("failure - byte arrays not same").isEqualTo(expected);
	}

	@Test
	public void testAssertEquals() {
		assertThat("text").as("failure - strings are not equal").isEqualTo("text");
	}

	@Test
	public void testAssertFalse() {
		assertThat(false).as("failure - should be false").isFalse();
	}

	@Test
	public void testAssertNotNull() {
		assertThat(new Object()).as("should not be null").isNotNull();
	}

	@Test
	public void testAssertNotSame() {
		assertThat(new Object()).as("should not be same Object").isNotSameAs(new Object());
	}

	@Test
	public void testAssertNull() {
		String str = null;
		assertThat(str).as("should be null").isNull();
	}

	@Test
	public void testAssertSame() {
		Integer aNumber = Integer.valueOf(768);
		assertThat(aNumber).as("should be same").isSameAs(aNumber);
	}

	@Test
	public void testAssertTrue() {
		assertThat(true).as("failure - should be true").isTrue();
		assertThat(true).isTrue();
	}
}
