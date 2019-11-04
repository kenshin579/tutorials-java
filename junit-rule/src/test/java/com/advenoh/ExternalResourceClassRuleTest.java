package kr.pe.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestFirstServer.class, TestSecondServer.class, TestThirdServer.class })
@Slf4j
public class ExternalResourceClassRuleTest {
	public static Server server = new Server();

	@ClassRule
	@Rule
	public static final ExternalResource externalResource = new ExternalResource() {
		@Override protected void before() throws Throwable {
			server.connect();
		}

		@Override protected void after() {
			server.disconnect();
		}
	};
}

