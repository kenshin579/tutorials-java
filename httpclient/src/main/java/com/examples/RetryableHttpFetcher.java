package com.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * https://nozaki.me/roller/kyle/entry/articles-test-wiremockunstable
 */
@Slf4j
public class RetryableHttpFetcher {

	// these parameters would be better to retrieve through JNDI or any other mechanism
	private int MAX_RETRIES = 3;
	private int RETRY_INTERVAL = 5000;
	private int TIMEOUT = 500;

	private final ServiceUnavailableRetryStrategy MY_SERVICE_UNAVAILABLE_RETRY_STRATEGY = new ServiceUnavailableRetryStrategy() {
		@Override
		public boolean retryRequest(HttpResponse response, int executionCount,
				org.apache.http.protocol.HttpContext context) {

			boolean rc = response.getStatusLine().getStatusCode() >= 500 && executionCount <= MAX_RETRIES;

			log.info("[FRANK] retryRequest(): returning={}, statusCode={}, executionCount={}, maxRetries={}, interval={}",
					rc, response.getStatusLine().getStatusCode(), executionCount, MAX_RETRIES, RETRY_INTERVAL);

			return rc;
		}

		@Override
		public long getRetryInterval() {
			return RETRY_INTERVAL;
		}
	};

	private final HttpRequestRetryHandler MY_HTTP_REQUEST_RETRY_HANDLER = (e, executionCount, context) -> {
		log.info("[FRANK] retryRequest(): exception={}, executionCount={}, maxRetries={}", e.getClass(), executionCount, MAX_RETRIES);

		if (executionCount > MAX_RETRIES) {
			log.info("[FRANK] give up: {0}", executionCount);
			return false;
		}

		if (e instanceof java.net.SocketTimeoutException) {
			log.info("[FRANK] retry: {0}", e.getMessage());
			return true;
		}

		log.info("[FRANK] not retry: {0}", e.getMessage());
		return false;
	};

	private final RequestConfig MY_REQUEST_CONFIG = RequestConfig.custom()
			.setConnectionRequestTimeout(TIMEOUT)
			.setConnectTimeout(TIMEOUT)
			.setSocketTimeout(TIMEOUT)
			.build();

	public String fetchAsString(String url) throws ClientProtocolException, IOException {

		try (CloseableHttpClient client = HttpClientBuilder.create()
				.setDefaultRequestConfig(MY_REQUEST_CONFIG)
				.setRetryHandler(MY_HTTP_REQUEST_RETRY_HANDLER)
				.setServiceUnavailableRetryStrategy(MY_SERVICE_UNAVAILABLE_RETRY_STRATEGY)
				.build()) {

			try (CloseableHttpResponse res = client.execute(new HttpGet(url))) {
				if (res.getStatusLine().getStatusCode() >= 400) {
					throw new HttpResponseException(res.getStatusLine().getStatusCode(), res.getStatusLine()
							.getReasonPhrase());
				}
				return EntityUtils.toString(res.getEntity());
			}
		}

	}
}
