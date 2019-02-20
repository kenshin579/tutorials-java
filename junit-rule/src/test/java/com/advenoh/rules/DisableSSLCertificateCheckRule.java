package com.advenoh.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

public class DisableSSLCertificateCheckRule implements TestRule {
	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				disableCertificateCheck();
				base.evaluate();
			}
		};
	}

	/**
	 * javax.net.ssl.SSLHandshakeException: 수동으로 certificate을 import해야 하는데,
	 * 한번 하고나도 몇주 지나면 다시 예외가 발생해서 코드상에서 disable 시키도록 함
	 * <p>
	 * https://stackoverflow.com/questions/4663147/is-there-a-java-setting-for-disabling-certificate-validation
	 */
	private void disableCertificateCheck() {
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

		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (GeneralSecurityException e) {
		}
	}

}
