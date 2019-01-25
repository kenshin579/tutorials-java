package com.java.examples;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NetworkServiceTest {

	@Test
	public void getConnection_네트워크_연결_안됨() throws IOException {
		NetworkService networkService = new NetworkService();
		networkService.startConnection("localhost", 123);
	}

	@Test
	public void getConnection_네트워크_연결_retry() {
		NetworkService networkService = new NetworkService();
		networkService.getConnectionWithRetry("localhost", 123);
	}

	@Test
	public void getConnectionWithRetries() throws Exception {
		NetworkService networkService = new NetworkService();
		assertThatThrownBy(() -> networkService.getConnectionWithRetries("localhost", 123, 3, 3000))
				.hasMessageContaining("Retry limit exceeded");
	}
}