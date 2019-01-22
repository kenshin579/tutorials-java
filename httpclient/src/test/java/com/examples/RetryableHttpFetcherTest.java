package com.examples;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import org.apache.http.client.HttpResponseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.net.SocketTimeoutException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RetryableHttpFetcherTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(18089);

	private RetryableHttpFetcher instance;

	@Before
	public void init() {
		instance = new RetryableHttpFetcher();
	}

	@Test
	public void test1_ok() throws Exception {
		stubFor(get(urlEqualTo("/hoge.txt")).willReturn(
				aResponse().withStatus(200).withHeader("Content-Type", "text/plain").withBody("OK")));
		String expected = "OK";

		String actual = instance.fetchAsString("http://localhost:18089/hoge.txt");

		assertThat(actual, is(expected));
	}

	@Test
	public void test2_retryAt500() throws Exception {
		stubFor(get(urlEqualTo("/500")).inScenario("retry at 500")
				.whenScenarioStateIs(Scenario.STARTED)
				.willSetStateTo("one time requested")
				.willReturn(aResponse().withBody("error").withStatus(500)));

		stubFor(get(urlEqualTo("/500")).inScenario("retry at 500")
				.whenScenarioStateIs("one time requested")
				.willReturn(aResponse().withBody("OK").withStatus(200)));

		String actual = instance.fetchAsString("http://localhost:18089/500");

		assertThat(actual, is("OK"));
	}

	@Test(expected = HttpResponseException.class)
	public void test3_retryAt500GiveUp() throws Exception {
		stubFor(get(urlEqualTo("/500"))
				.willReturn(aResponse().withBody("500").withStatus(500)));

		instance.fetchAsString("http://localhost:18089/500");
	}

	@Test
	public void test4_retryAtTimeout() throws Exception {
		stubFor(get(urlEqualTo("/timeout")).inScenario("retrying")
				.whenScenarioStateIs(Scenario.STARTED)
				.willSetStateTo("one time requested")
				.willReturn(aResponse().withBody("error").withStatus(500).withFixedDelay(3000)));
		stubFor(get(urlEqualTo("/timeout")).inScenario("retrying")
				.whenScenarioStateIs("one time requested")
				.willReturn(aResponse().withBody("OK").withStatus(200)));

		String actual = instance.fetchAsString("http://localhost:18089/timeout");
		assertThat(actual, is("OK"));
	}

	@Test(expected = SocketTimeoutException.class)
	public void test5_retryAtTimeoutGiveUp() throws Exception {
		stubFor(get(urlEqualTo("/timeout"))
				.willReturn(aResponse().withBody("timeout").withStatus(500).withFixedDelay(Integer.MAX_VALUE)));

		instance.fetchAsString("http://localhost:18089/timeout");
	}

	@Test(expected = HttpResponseException.class)
	public void notFound() throws Exception {
		instance.fetchAsString("http://localhost:18089/NOT_FOUND");
	}
}