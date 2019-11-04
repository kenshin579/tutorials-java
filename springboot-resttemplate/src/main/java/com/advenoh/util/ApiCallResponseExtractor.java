package kr.pe.advenoh.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.HttpMessageConverterExtractor;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ApiCallResponseExtractor extends HttpMessageConverterExtractor<String> {

	public ApiCallResponseExtractor(Class<String> responseType,
			List<HttpMessageConverter<?>> messageConverters) {
		super(responseType, messageConverters);
	}

	@Override
	public String extractData(ClientHttpResponse response) throws IOException {

		String result;

		if (response.getStatusCode() == HttpStatus.OK) {
			Scanner scanner = new java.util.Scanner(response.getBody()).useDelimiter("[\\(\\)]");
			scanner.next(); // callback name,
			String json = scanner.next();
			result = json;

		} else {
			result = null;
		}

		return result;
	}
}