package com.advenoh.streams;

import com.advenoh.SchedulerType;
import com.advenoh.vod.VodCollection;
import com.advenoh.vod.enums.VodCollectionType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StreamTest {
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

	@Test
	public void test_collectionType별로_List생성하기() throws JsonProcessingException {
		List<VodCollection> vodCollectionList = createVodCollection(3);
		log.info("vodCollectionList : {}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(vodCollectionList));

		Map<VodCollectionType, List<VodCollection>> vodCollectionMap = vodCollectionList.stream()
				.collect(Collectors.groupingBy(VodCollection::getVodCollectionType));

		log.info("vodCollectionMap : {}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(vodCollectionMap));
	}

	private List<VodCollection> createVodCollection(int max) {
		List<VodCollection> vodCollectionList = new ArrayList<>();
		for (int i = 0; i < max; i++) {
			vodCollectionList.add(VodCollection.builder()
					.title("title" + i)
					.content("content" + i)
					.vodCollectionType(VodCollectionType.getRandomType())
					.build());
		}
		return vodCollectionList;
	}

	private boolean getResult(SchedulerType schedulerType) {
		boolean result = true;
		if (schedulerType == SchedulerType.LIVE_DEAL_PURCHASE) {
			result = false;
		}
		log.info("schedulerType : {} result : {}", schedulerType, result);
		return result;
	}

	//todo : List<Map<String, Long>> -> List<Long>으로 변환하기
//	private List<Long> getAllVodNos() throws JsonProcessingException {
//		List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT vod_seqno FROM tmon_media.media_vod");
//		return maps.stream().map(m -> ((BigInteger) m.get("vod_seqno")).longValue()).collect(Collectors.toList());
//	}

}
