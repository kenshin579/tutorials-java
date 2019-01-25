package com.java.examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Slf4j
public class CompletableFutureTest {

	static Random random = new Random();

	@Test
	public void whenRunningCompletableFutureAsynchronously_thenGetMethodWaitsForResult() throws InterruptedException, ExecutionException {
		Future<String> completableFuture = calculateAsync();

		String result = completableFuture.get();
		assertEquals("Hello", result);
	}

	private Future<String> calculateAsync() throws InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		Executors.newCachedThreadPool()
				.submit(() -> {
					log.info("sleeping...");
					Thread.sleep(2000);
					log.info("START calling complete");
					completableFuture.complete("Hello");
					log.info("END calling complete");
					return null;
				});

		return completableFuture;
	}

	@Test
	public void whenRunningCompletableFutureWithResult_thenGetMethodReturnsImmediately() throws InterruptedException, ExecutionException {
		Future<String> completableFuture = CompletableFuture.completedFuture("Hello");

		String result = completableFuture.get();
		assertEquals("Hello", result);
	}

	private Future<String> calculateAsyncWithCancellation() throws InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		Executors.newCachedThreadPool()
				.submit(() -> {
					log.info("sleeping..");
					Thread.sleep(500);
					log.info("future canceling...");
					completableFuture.cancel(false);
					return null;
				});

		return completableFuture;
	}

	@Test(expected = CancellationException.class)
	public void whenCancelingTheFuture_thenThrowsCancellationException() throws ExecutionException, InterruptedException {
		Future<String> future = calculateAsyncWithCancellation();
		future.get(); // CancellationException
	}

	@Test
	public void whenCreatingCompletableFutureWithSupplyAsync_thenFutureReturnsValue() throws ExecutionException, InterruptedException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

		assertEquals("Hello", future.get());
	}

	@Test
	public void whenAddingThenAcceptToFuture_thenFunctionExecutesAfterComputationIsFinished() throws ExecutionException, InterruptedException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

		CompletableFuture<Void> future = completableFuture.thenAccept(s -> log.debug("Computation returned: " + s));

		future.get();
	}

	@Test
	public void whenAddingThenRunToFuture_thenFunctionExecutesAfterComputationIsFinished() throws ExecutionException, InterruptedException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

		CompletableFuture<Void> future = completableFuture.thenRun(() -> log.debug("Computation finished."));

		future.get();
	}

	@Test
	public void whenAddingThenApplyToFuture_thenFunctionExecutesAfterComputationIsFinished() throws ExecutionException, InterruptedException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

		CompletableFuture<String> future = completableFuture.thenApply(s -> s + " World");

		assertEquals("Hello World", future.get());
	}

	@Test
	public void whenUsingThenCompose_thenFuturesExecuteSequentially() throws ExecutionException, InterruptedException {
		CompletableFuture<String> completableFuture = CompletableFuture
				.supplyAsync(() -> "Hello")
				.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

		assertEquals("Hello World", completableFuture.get());
	}

	@Test
	public void whenUsingThenCombine_thenWaitForExecutionOfBothFutures() throws ExecutionException, InterruptedException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
				.thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);

		assertEquals("Hello World", completableFuture.get());
	}

	@Test
	public void whenUsingThenAcceptBoth_thenWaitForExecutionOfBothFutures() throws ExecutionException, InterruptedException {
		CompletableFuture.supplyAsync(() -> "Hello")
				.thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> log.debug(s1 + s2));
	}

	@Test
	public void whenFutureCombinedWithAllOfCompletes_thenAllFuturesAreDone() throws ExecutionException, InterruptedException {
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

		// ...

		combinedFuture.get();

		assertTrue(future1.isDone());
		assertTrue(future2.isDone());
		assertTrue(future3.isDone());

		String combined = Stream.of(future1, future2, future3)
				.map(CompletableFuture::join)
				.collect(Collectors.joining(" "));

		assertEquals("Hello Beautiful World", combined);
	}

	@Test
	public void whenFutureThrows_thenHandleMethodReceivesException() throws ExecutionException, InterruptedException {
		String name = null;

		// ...

		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
			if (name == null) {
				throw new RuntimeException("Computation error!");
			}
			return "Hello, " + name;
		}).handle((s, t) -> s != null ? s : "Hello, Stranger!");

		assertEquals("Hello, Stranger!", completableFuture.get());
	}

	@Test(expected = ExecutionException.class)
	public void whenCompletingFutureExceptionally_thenGetMethodThrows() throws ExecutionException, InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		// ...

		completableFuture.completeExceptionally(new RuntimeException("Calculation failed!"));

		// ...

		completableFuture.get();
	}

	@Test
	public void whenAddingThenApplyAsyncToFuture_thenFunctionExecutesAfterComputationIsFinished() throws ExecutionException, InterruptedException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

		CompletableFuture<String> future = completableFuture.thenApplyAsync(s -> s + " World");

		assertEquals("Hello World", future.get());
	}

	@Test
	public void whenPassingTransformation_thenFunctionExecutionWithThenApply() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> finalResult = compute().thenApply(s -> s + 1);
		assertTrue(finalResult.get() == 11);
	}

	@Test
	public void whenPassingPreviousStage_thenFunctionExecutionWithThenCompose() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> finalResult = compute().thenCompose(this::computeAnother);
		assertTrue(finalResult.get() == 20);
	}

	@Test
	public void test_runAsync_바로_실행됨() {
		CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
			log.info("start cf");
			assertTrue(Thread.currentThread().isDaemon());
			randomSleep();
			log.info("end cf");
		});

		log.info("1");
		assertFalse(cf.isDone());
		log.info("2");
		sleepEnough();
		log.info("3");
		assertTrue(cf.isDone());
		log.info("4");
	}

	@Test
	public void test_thenApplyVsThenApplyAsync() {
		//todo: 아직 잘 모르겠음.
		CompletableFuture<Integer> future
				= CompletableFuture.supplyAsync(() -> 0);

		future.thenApply(x -> x + 1)
				.thenApply(x -> x + 1)
				.thenAccept(x -> System.out.println("thenApply: " + x));

		future.thenApplyAsync(x -> x + 1)   // first step
				.thenApplyAsync(x -> x + 1)   // second step
				.thenAccept(x -> System.out.println("thenApplyAsync: " + x)); // third step
	}

	@Test
	public void testSupplyAsync() {
		CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
			log.info("start supplyAsync");
			randomSleep();
			log.info("end supplyAsync");
			return "result A on thread " + Thread.currentThread().getId();
		})
				.thenApply(str -> str + "+ tailed")
				.thenAccept(finalResult -> System.out.println(finalResult));

		System.out.println("Task execution requested on on thread " + Thread.currentThread().getId());

		assertFalse(cf.isDone());
		sleepEnough();
		assertTrue(cf.isDone());
	}

	@Test
	public void testSupplyAsync2() {
		CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
			log.info("start supplyAsync");
			randomSleep();
			log.info("end supplyAsync");
			return "result A on thread " + Thread.currentThread().getId();
		})
				.thenApplyAsync(str -> str + "+ tailed")
				.thenAccept(finalResult -> System.out.println(finalResult));

		System.out.println("Task execution requested on on thread " + Thread.currentThread().getId());

		assertFalse(cf.isDone());
		sleepEnough();
		assertTrue(cf.isDone());
	}

	public CompletableFuture<Integer> compute() {
		return CompletableFuture.supplyAsync(() -> 10);
	}

	public CompletableFuture<Integer> computeAnother(Integer i) {
		return CompletableFuture.supplyAsync(() -> 10 + i);
	}

	private static void randomSleep() {
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
			// ...
		}
	}

	private static void sleepEnough() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// ...
		}
	}
}
