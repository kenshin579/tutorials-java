package kr.pe.advenoh;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GuavaTest {
	@Test
	public void test_partition_list() {
		int MAX_NUM = 2100;
		int MAX_LIMIT_REQUEST_FOR_MULTI_OBJECTS_DELETE = 1000;

		List<String> fileList = new ArrayList<>();

		//filelist 생성
		for (int i = 0; i < MAX_NUM; i++) {
			fileList.add(String.valueOf(i));
		}

		List<List<String>> lists = Lists.partition(fileList, MAX_LIMIT_REQUEST_FOR_MULTI_OBJECTS_DELETE);
		log.info("lists: {}", lists);

		for (List<String> subList : lists) {
			log.info("{}:{}", subList.get(0), subList.get(subList.size() - 1));
		}
	}
}
