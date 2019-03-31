package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

import static com.advenoh.ConcurrentUtils.stop;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class AtomicIntegerTest {
	@Test
	public void test_atomic_incrementAndGet() {
		int MAX = 10;
		AtomicInteger atomicInt = new AtomicInteger(0);
		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, MAX)
				.forEach(i -> {
					Runnable task = () -> {
						log.info("threadId:{} - i:{} - value:{}", Thread.currentThread().getId(), i, atomicInt.get());
						atomicInt.incrementAndGet();
					};
					executor.submit(task);
				});

		stop(executor);
		assertThat(atomicInt.get()).isEqualTo(MAX); //1000
	}

	@Test
	public void test_atomic_updateAndGet() {
		int MAX = 10;
		AtomicInteger atomicInt = new AtomicInteger(0);
		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, MAX)
				.forEach(i -> {
					Runnable task = () -> {
						log.info("threadId:{} - i:{} - value:{}", Thread.currentThread().getId(), i, atomicInt.get());
						atomicInt.updateAndGet(n -> n + 2);
					};
					executor.submit(task);
				});

		stop(executor);
		assertThat(atomicInt.get()).isEqualTo(MAX * 2); //1000
	}

	@Test
	public void test_atomic_accumulateAndGet() {
		int MAX = 10;
		AtomicInteger atomicInt = new AtomicInteger(0);
		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, MAX)
				.forEach(i -> {
					Runnable task = () -> {
						log.info("threadId:{} - i:{} - value:{}", Thread.currentThread().getId(), i, atomicInt.get());
						atomicInt.accumulateAndGet(i, (n, m) -> n + m);
					};
					executor.submit(task);
				});

		stop(executor);

		System.out.println(atomicInt.get());    // => 499500
	}

	@Test
	public void test_LongAdder() {
		int MAX = 1000;
		LongAdder adder = new LongAdder();
		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, MAX)
				.forEach(i -> executor.submit(adder::increment));

		stop(executor);

		assertThat(adder.sumThenReset()).isEqualTo(MAX);   // => 1000
	}
}
