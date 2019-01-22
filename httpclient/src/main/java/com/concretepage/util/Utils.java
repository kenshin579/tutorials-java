package com.concretepage.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

@Slf4j
public class Utils {
	private static int MAX_RETRIES = 3;
	private static int RETRY_INTERVAL = 10000;
	private static int TIMEOUT = 500;

	public static CloseableHttpResponse sendDataViaPost(String requestUrl, String requestBody) {
		int numRetries = 5;
		int maxTimeout = 120 * 1000;
		CloseableHttpResponse response = null;

		try {

			//			CloseableHttpClient client = HttpClients.createDefault();
			//			client.set(new DefaultHttpRequestRetryHandler(0, false));

			CloseableHttpClient client = HttpClients.custom()
					.setServiceUnavailableRetryStrategy(new ServiceUnavailableRetryStrategy() {
						//						@Override
						//						public boolean retryRequest(final HttpResponse response, final int executionCount, final HttpContext context) {
						//							int statusCode = response.getStatusLine().getStatusCode();
						//							log.info("statusCode: ", statusCode);
						//							return statusCode != 200 && executionCount < 5;
						//						}

						@Override
						public boolean retryRequest(HttpResponse response, int executionCount,
								org.apache.http.protocol.HttpContext context) {

							boolean rc = response.getStatusLine().getStatusCode() != HttpStatus.SC_OK && executionCount <= MAX_RETRIES;

							log.info("retryRequest(): returning={}, statusCode={}, executionCount={}, maxRetries={}, interval={}",
									rc, response.getStatusLine().getStatusCode(), executionCount, MAX_RETRIES, RETRY_INTERVAL);

							return rc;
						}

						@Override public long getRetryInterval() {
							return RETRY_INTERVAL;
						}
					}).build();

			//timeout 설정하지 않으면 무한 대기함
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(maxTimeout)
					.setConnectTimeout(maxTimeout)
					.setSocketTimeout(maxTimeout).build();
			//			HttpGet request = new HttpGet(SAMPLE_URL);

			HttpPost httpPost = new HttpPost(requestUrl);
			httpPost.setConfig(requestConfig);

			StringEntity entity = new StringEntity(requestBody);

			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			response = client.execute(httpPost);
			//			while (true) {
			//
			//				log.info("response: {}", response);
			//
			//				if (response.getStatusLine().getStatusCode() != 200) {
			//					numRetries--;
			//					if (numRetries < 0) {
			//						log.warn("Retry limit exceeded: response: {}", response);
			//						break;
			//					}
			//				} else {
			//					break;
			//				}
			//
			//				try {
			//					TimeUnit.SECONDS.sleep(3);
			//				} catch (InterruptedException e) {
			//					log.warn("sleep error", e);
			//				}
			//			}

			client.close();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		//		assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
		return response;
	}
}
