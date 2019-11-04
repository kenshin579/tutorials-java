package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

@Slf4j
public class ExternalResourceRuleTest {
	public static Server server =  new Server();

	@Rule
	public final ExternalResource externalResource = new ExternalResource() {
		@Override protected void before() throws Throwable {
			server.connect();
		}

		@Override protected void after() {
			server.disconnect();
		}
	};

	@Test
	public void 서버테스트() throws Exception {
		log.info("Start 서버 테스트");
	}
}

