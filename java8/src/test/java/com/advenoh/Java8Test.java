package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class Java8Test {
	@Test
	public void test_stream으로_getResult_메서드의_총_결과를_반환하는_예제() {
		boolean result;
		result = Stream.of(SchedulerType.values())
				.filter(s1 -> s1 != SchedulerType.ALL)
				.allMatch(st -> getResult(st));
		assertThat(result).isFalse();
		log.info("-------------------------------------------------");

		result = Stream.of(SchedulerType.values())
				.filter(s1 -> s1 != SchedulerType.ALL)
				.filter(s1 -> s1 != SchedulerType.LIVE_DEAL_PURCHASE)
				.allMatch(st -> getResult(st));
		assertThat(result).isTrue();
	}

	private boolean getResult(SchedulerType schedulerType) {
		boolean result = true;
		if (schedulerType == SchedulerType.LIVE_DEAL_PURCHASE) {
			result = false;
		}
		log.info("schedulerType : {} result : {}", schedulerType, result);
		return result;
	}
}
