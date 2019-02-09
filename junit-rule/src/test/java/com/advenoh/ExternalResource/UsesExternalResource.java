package com.advenoh.ExternalResource;

import com.advenoh.Server;
import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ A.class, B.class, C.class })
public class UsesExternalResource {
	public static Server myServer = new Server();

	@ClassRule
	public static ExternalResource resource = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			myServer.connect();
		}

		@Override
		protected void after() {
			myServer.disconnect();
		}
	};
}