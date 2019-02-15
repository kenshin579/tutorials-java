package com.advenoh;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DisableSSLCertificateCheckRuleTest {
//	final String SSL_API_URL = "https://api.github.com";
	final String SSL_API_URL = "https://api.bcovlive.io";

	@Test
	public void test_after_import_certificate() {
		Assertions.assertThatCode(this::connectHttps).doesNotThrowAnyException();
	}

	private void connectHttps() {
		try {
			URL u = new URL(SSL_API_URL);
			u.openStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
