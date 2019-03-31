package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static com.advenoh.ConcurrentUtils.stop;

@Slf4j
public class AtomicIntegerTest {
	@Test
	public void test_atomic() {
		AtomicInteger atomicInt = new AtomicInteger(0);

		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, 1000)
				.forEach(i -> executor.submit(atomicInt::incrementAndGet));

		stop(executor);

		System.out.println(atomicInt.get());    // => 1000
	}

	@Test
	public void name() {
		AtomicInteger atomicInt = new AtomicInteger(0);

		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, 1000)
				.forEach(i -> {
					Runnable task = () ->
							atomicInt.updateAndGet(n -> n + 2);
					executor.submit(task);
				});

		stop(executor);

		System.out.println(atomicInt.get());    // => 2000
	}
}
