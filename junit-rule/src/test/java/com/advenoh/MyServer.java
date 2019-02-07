package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.rules.ExternalResource;

@Slf4j
public class MyServer extends ExternalResource {
	@Override
	protected void before() throws Throwable {
		// start the server
		log.info("start the server");
	}

	@Override
	protected void after() {
		// stop the server
		log.info("stop the server");
	}
}