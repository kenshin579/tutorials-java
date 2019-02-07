package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//todo: 이거 잘 이해가 안됨

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestFirstServer.class, TestSecondServer.class })
@Slf4j
public class ServerTest {
	@ClassRule
	public static MyServer server = new MyServer();

	@Test
	public void testBlah() throws Exception {
		// test something that depends on the server.
		log.info("ServerTest... testBlah");
	}
}

