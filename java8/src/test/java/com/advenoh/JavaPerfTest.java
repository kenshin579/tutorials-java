package kr.pe.advenoh;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class JavaPerfTest {

	@Test
	public void name1() {
		int MAX_INTERVAL = 3;
		int MAX_SUB = 10000;
		int MAX = 10000;
		int expectedSize = MAX * MAX_SUB;

		StopWatch sw = new StopWatch();
		List<String> subList = createSubList(MAX_SUB);

		List<String> result = null;
		for (int j = 0; j < MAX_INTERVAL; j++) {
			sw.start("java merge-" + j);
			result = mergeArrayList(MAX, subList);
			sw.stop();
			assertThat(result.size()).isEqualTo(expectedSize);
		}

		log.info("sw: {}", sw.prettyPrint());
		log.info("sw: {}", sw.getTotalTimeMillis() / MAX_INTERVAL);
	}

	@Test
	public void name2() {
		int MAX_INTERVAL = 3;
		int MAX_SUB = 10000;
		int MAX = 10000;
		int expectedSize = MAX * MAX_SUB;

		StopWatch sw = new StopWatch();
		List<String> subList = createSubList(MAX_SUB);

		List<String> result = null;
		for (int j = 0; j < MAX_INTERVAL; j++) {
			sw.start("collection-" + j);
			result = collectionAddAll(MAX, subList);
			sw.stop();
			assertThat(result.size()).isEqualTo(expectedSize);
		}

		log.info("sw: {}", sw.prettyPrint());
		log.info("sw: {}", sw.getTotalTimeMillis() / MAX_INTERVAL);
	}

	@Test
	public void name3() {
		int MAX_INTERVAL = 3;
		int MAX_SUB = 10000;
		int MAX = 10000;
		int expectedSize = MAX * MAX_SUB;

		StopWatch sw = new StopWatch();

		List<String> subList = createSubList(MAX_SUB);
		List<String> result = null;
		for (int j = 0; j < MAX_INTERVAL; j++) {
			sw.start("iterablesAddAll-" + j);
			result = iterablesAddAll(MAX, subList);
			sw.stop();
			assertThat(result.size()).isEqualTo(expectedSize);
		}

		log.info("sw: {}", sw.prettyPrint());
		log.info("sw: {}", sw.getTotalTimeMillis() / MAX_INTERVAL);

	}

	@Test
	public void name4() {
		int MAX_INTERVAL = 3;
		int MAX_SUB = 10000;
		int MAX = 10000;
		int expectedSize = MAX * MAX_SUB;

		StopWatch sw = new StopWatch();

		List<String> subList = createSubList(MAX_SUB);

		List<String> result = null;
		for (int j = 0; j < MAX_INTERVAL; j++) {
			sw.start("streams-" + j);
			result = streams(MAX, subList);
			sw.stop();
		}
		assertThat(result.size()).isEqualTo(expectedSize);

		log.info("sw: {}", sw.prettyPrint());
		log.info("sw: {}", sw.getTotalTimeMillis() / MAX_INTERVAL);
	}

	private List<String> createSubList(int MAX_SUB) {
		List<String> subList = new ArrayList<>();
		for (int i = 0; i < MAX_SUB; i++) {
			subList.add(String.valueOf(i));
		}
		return subList;
	}

	private List<String> mergeArrayList(int MAX, List<String> subList) {
		List<String> fileList = new ArrayList<>();

		for (int i = 0; i < MAX; i++) {
			fileList.addAll(subList);
		}

		return fileList;
	}

	private List<String> collectionAddAll(int MAX, List<String> subList) {
		List<String> fileList = new ArrayList<>();

		for (int i = 0; i < MAX; i++) {
			Collections.addAll(fileList, subList.toArray(new String[0]));
		}

		return fileList;
	}

	private List<String> iterablesAddAll(int MAX, List<String> subList) {
		List<String> fileList = Lists.newArrayList();
		for (int i = 0; i < MAX; i++) {
			Iterables.addAll(fileList, subList);
		}
		return fileList;
	}

	private List<String> streams(int MAX, List<String> subList) {
		List<String> fileList = new ArrayList<>();

		for (int i = 0; i < MAX; i++) {
			Stream.of(subList).forEach(fileList::addAll);
		}
		return fileList;
	}

}
