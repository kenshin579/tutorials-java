package kr.pe.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TemporaryRuleTest {
	private final String TEXT_FILE = "src/test/resources/test.txt";

	@Rule
	public TemporaryFolder tmpFolder = new TemporaryFolder();

	@Test
	public void test_임시파일_생성하기() throws IOException {
		//var/folders/f3/z3w0kdln2sn_7z0_qq6rn4dxrgwgh2/T/junit88560316993858696/test.txt
		File tmpFile = tmpFolder.newFile("test.txt");
		assertThat(tmpFile.isFile()).isTrue();
		assertThat(tmpFolder.getRoot()).isEqualTo(tmpFile.getParentFile());
	}

	@Test
	public void test_임시_폴더_생성하기() throws IOException {
		File tmpFile = tmpFolder.newFolder();
		assertThat(tmpFile.isDirectory()).isTrue();
	}

	@Test
	public void test_임지() throws IOException {
		File tmpFile = copyFile(new File(TEXT_FILE), tmpFolder.newFile(FilenameUtils.getName(TEXT_FILE)));
		BufferedReader reader = new BufferedReader(new FileReader(tmpFile));
		assertThat(reader.readLine()).isEqualTo("hello world");
	}

	private static File copyFile(File srcFile, File destFile) throws IOException {
		FileUtils.copyFile(srcFile, destFile);
		return destFile;
	}
}
