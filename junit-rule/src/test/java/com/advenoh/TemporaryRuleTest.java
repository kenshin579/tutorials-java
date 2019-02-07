package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TemporaryRuleTest {
	@Rule
	public TemporaryFolder tmpFolder = new TemporaryFolder();

	/**
	 * //var/folders/f3/z3w0kdln2sn_7z0_qq6rn4dxrgwgh2/T/junit88560316993858696/test.txt
	 *
	 * @throws IOException
	 */
	@Test
	public void test_임시파일_생성하기() throws IOException {
		File tmpFile = tmpFolder.newFile("test.txt");
		assertThat(tmpFile.isFile()).isTrue();
		assertThat(tmpFolder.getRoot()).isEqualTo(tmpFile.getParentFile());
	}

	@Test
	public void test_임시_폴더_생성하기() throws IOException {
		File tmpFile = tmpFolder.newFolder();
		assertThat(tmpFile.isDirectory()).isTrue();
	}
}
