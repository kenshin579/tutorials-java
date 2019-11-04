package com.advenoh;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SomeService {
	public String someMethod(SomeEntity someEntity) {
		return someEntity.getSomeProperty();
	}

	public void requestJobId(String jobId) {
		try {
			throwMethodTest("throwing test");
		} catch (Exception e) {
			log.error("[servicedebug] error occurred : jobId : {}", jobId);
		}

	}

	private void throwMethodTest(String msg) throws Exception {
		throw new Exception(msg);
	}

}