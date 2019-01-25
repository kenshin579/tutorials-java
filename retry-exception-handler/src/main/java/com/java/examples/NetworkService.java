package com.java.examples;

import com.oracle.retry.RetryOnException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NetworkService {

	public SocketClient startConnection(String ip, int port) {
		SocketClient client = new SocketClient();
		try {
			client.startConnection(ip, port);
		} catch (IOException e) {
			log.error("error", e);
		}
		return client;
	}

	public SocketClient getConnectionWithRetry(String ip, int port) {
		SocketClient client = new SocketClient();
		int numRetries = 3;

		while (true) {
			try {
				client.startConnection(ip, port);
			} catch (IOException e) {
				log.info("retry connection...", e);

				numRetries--;
				if (numRetries < 0) {
					log.warn("Retry limit exceeded");
					break;
				}

				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e1) {
					log.error("sleep error", e1);
				}

			}
		}
		return client;
	}

	public SocketClient getConnectionWithRetries(String ip, int port, int retries, long retrySleep) throws Exception {
		SocketClient client = new SocketClient();
		RetryOnException retryHandler = new RetryOnException(retries, retrySleep);

		while (true) {
			try {
				client.startConnection(ip, port);
			} catch (Exception ex) {
				log.error("error", ex);
				retryHandler.exceptionOccurred();
			}
		}
	}
}
