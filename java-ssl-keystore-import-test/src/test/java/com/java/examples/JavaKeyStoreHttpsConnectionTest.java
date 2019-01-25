package com.java.examples;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

public class JavaKeyStoreHttpsConnectionTest {
	final String SSL_API_URL = "https://app.zencoder.com";

	/**
	 * 자바 keystore에 certificate을 import을 하지 않아도 그냥 되어야 함
	 */
	@Test
	public void test_disable_certificate_from_code() {
		disableCertificateCheck();
		Assertions.assertThatCode(this::connectHttps).doesNotThrowAnyException();
	}

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

	private void disableCertificateCheck() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] {
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return new X509Certificate[0];
					}

					public void checkClientTrusted(
							java.security.cert.X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(
							java.security.cert.X509Certificate[] certs, String authType) {
					}
				}
		};

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (GeneralSecurityException e) {
		}
	}
}
