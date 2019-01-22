package com.concretepage.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Utils {
	public static CloseableHttpResponse sendDataViaPost(String requestUrl, String requestBody) {
		int numRetries = 5;
		CloseableHttpResponse response = null;

		try {
			CloseableHttpClient client = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost(requestUrl);

			StringEntity entity = new StringEntity(requestBody);

			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			while (true) {
				response = client.execute(httpPost);
				log.info("response: {}", response);

				if (response.getStatusLine().getStatusCode() != 200) {
					numRetries--;
					if (numRetries < 0) {
						log.warn("Retry limit exceeded: response: {}", response);
						break;
					}
				} else {
					break;
				}

				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					log.warn("sleep error", e);
				}
			}

			client.close();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		//		assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
		return response;
	}
}
