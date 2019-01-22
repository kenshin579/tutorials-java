package com.concretepage.controller;

import com.concretepage.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class HttpClientTest {

	@Test
	public void testHttpUrlConnection() {
		String requestUrl = "http://localhost:8080/data/saveJson/123";

		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("status", "finished");
		requestBody.put("cdnUrl", "http://localhost:8080/cdnUrl");

		try {
			ObjectMapper om = new ObjectMapper();
			String payload = om.writeValueAsString(requestBody);

			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			writer.write(payload);
			writer.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer jsonString = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				jsonString.append(line);
			}
			br.close();
			connection.disconnect();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * https://www.baeldung.com/httpclient-post-http-request
	 */
	@Test
	public void testHttpClient() throws IOException {
		String requestUrl = "http://localhost:8080/data/saveJson/123";

		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(requestUrl);

		String requestBody = "{ \"status\": \"finished\", \"cdnUrl\": \"http://localhost:8080/cdn\"}";
		StringEntity entity = new StringEntity(requestBody);

		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		CloseableHttpResponse response = client.execute(httpPost);
		assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
		client.close();
	}

	@Test
	public void sendDataViaPost_200_정상인_경우() {
		String requestUrl = "http://localhost:7070/data/saveJson/123";
		String requestBody = "{ \"status\": \"finished\", \"cdnUrl\": \"http://localhost:8080/cdn\"}";

		assertThat(Utils.sendDataViaPost(requestUrl, requestBody).getStatusLine().getStatusCode()).isEqualTo(200);
	}

	//todo: 잘 안됨....
	@Test
	public void sendDataViaPost_200이_아닌_경우_retry2() {
		String requestUrl = "http://localhost:7070/data/1saveJson/123";
		String requestBody = "{ \"status\": \"finished\", \"cdnUrl\": \"http://localhost:8080/cdn\"}";

		Utils.sendDataViaPost(requestUrl, requestBody);

	}
}