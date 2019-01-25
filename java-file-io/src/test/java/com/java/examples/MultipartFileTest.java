package com.java.examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.util.Random;

import static com.java.examples.FileUtil.createDuplicateCopyOfMultipartFile;

@Slf4j
public class MultipartFileTest {
	static Random random = new Random();

	private final String testDir = "src/test/resources/testfile";

	private final String VIDEO_FILE = "src/test/resources/testfile/animation_clip.mp4";
	private MockMultipartFile testMockVideoFile;

	@Before
	public void setUp() throws Exception {
		testMockVideoFile = createDuplicateCopyOfMultipartFile("file", VIDEO_FILE);
	}

	@After
	public void tearDown() throws Exception {
		String filename = testMockVideoFile.getOriginalFilename();
		File file = new File(testDir + "/" + testMockVideoFile.getOriginalFilename());

		if (file.exists()) {
			file.delete();
		} else {
			log.warn("not exists: {}", filename);
		}
	}

	@Test
	public void test1() {
		log.info("testFilename: {}", testMockVideoFile.getOriginalFilename());
	}

	@Test
	public void test2() {
		log.info("testFilename: {}", testMockVideoFile.getOriginalFilename());
	}

	@Test
	public void test3() {
		log.info("testFilename: {}", testMockVideoFile.getOriginalFilename());
	}

	@Test
	public void test4() {
		log.info("testFilename: {}", testMockVideoFile.getOriginalFilename());
	}

	@Test
	public void test5() {
		log.info("testFilename: {}", testMockVideoFile.getOriginalFilename());
	}

	@Test
	public void test6() {
		log.info("testFilename: {}", testMockVideoFile.getOriginalFilename());
	}

	@Test
	public void test7() {
		log.info("testFilename: {}", testMockVideoFile.getOriginalFilename());
	}

	@Test
	public void test8() {
		log.info("testFilename: {}", testMockVideoFile.getOriginalFilename());
	}
}
