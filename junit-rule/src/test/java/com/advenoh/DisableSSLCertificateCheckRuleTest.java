package kr.pe.advenoh;

import kr.pe.advenoh.rules.DisableSSLCertificateCheckRule;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class DisableSSLCertificateCheckRuleTest {
	@ClassRule
	public DisableSSLCertificateCheckRule disableSSLRule = new DisableSSLCertificateCheckRule();

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
