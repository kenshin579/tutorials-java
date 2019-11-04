package kr.pe.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestFirstServer {
	@Test
	public void test() throws Exception {
		log.info("{}", this.getClass().getSimpleName());
	}
}