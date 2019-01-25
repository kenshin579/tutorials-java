package com.java.examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Random;

import static com.java.examples.FileUtil.createDuplicateCopyOfFile;

@Slf4j
public class FileTest {
	static Random random = new Random();

	private final String testDir = "src/test/resources/testfile";

	private final String VIDEO_FILE = "src/test/resources/testfile/animation_clip.mp4";
	private File videoFile;

	@Before
	public void setUp() throws Exception {
		videoFile = createDuplicateCopyOfFile(VIDEO_FILE);
	}

	@After
	public void tearDown() throws Exception {
		String filename = videoFile.getName();
		File file = new File(testDir + "/" + videoFile.getName());

		if (file.exists()) {
			file.delete();
		} else {
			log.warn("not exists: {}", filename);
		}
	}

	@Test
	public void test1() {
		log.info("testFilename: {}", videoFile.getName());
	}

	@Test
	public void test2() {
		log.info("testFilename: {}", videoFile.getName());
	}

	@Test
	public void test3() {
		log.info("testFilename: {}", videoFile.getName());
	}

	@Test
	public void test4() {
		log.info("testFilename: {}", videoFile.getName());
	}

	@Test
	public void test5() {
		log.info("testFilename: {}", videoFile.getName());
	}

	@Test
	public void test6() {
		log.info("testFilename: {}", videoFile.getName());
	}

	@Test
	public void test7() {
		log.info("testFilename: {}", videoFile.getName());
	}

	@Test
	public void test8() {
		log.info("testFilename: {}", videoFile.getName());
	}

}
