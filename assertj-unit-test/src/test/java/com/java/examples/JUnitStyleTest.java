package com.java.examples;

import org.junit.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class JUnitStyleTest {
	@Test
	public void testAssertArrayEquals() {
		byte[] expected = "trial".getBytes();
		byte[] actual = "trial".getBytes();
		assertArrayEquals("failure - byte arrays not same", expected, actual);
	}

	@Test
	public void testAssertEquals() {
		assertEquals("failure - strings are not equal", "text", "text");
	}

	@Test
	public void testAssertFalse() {
		assertFalse("failure - should be false", false);
	}

	@Test
	public void testAssertNotNull() {
		assertNotNull("should not be null", new Object());
	}

	@Test
	public void testAssertNotSame() {
		assertNotSame("should not be same Object", new Object(), new Object());
	}

	@Test
	public void testAssertNull() {
		String str = null;
		assertNull("should be null", str);
	}

	@Test
	public void testAssertSame() {
		Integer aNumber = Integer.valueOf(768);
		assertSame("should be same", aNumber, aNumber);
	}

	@Test
	public void testAssertTrue() {
		assertTrue("failure - should be true", true);
	}
}
