package com.java.examples;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class FileUtil {
	public static MockMultipartFile createDuplicateCopyOfMultipartFile(String paraName, String relativePathFilename) throws IOException {
		return createMockMultipartFile(paraName, createDuplicateCopyOfFile(relativePathFilename));
	}

	public static File createDuplicateCopyOfFile(String relativePathFilename) throws IOException {
		File originalFile = new File(relativePathFilename);
		String newFilename = originalFile.getParent() + "/" + FilenameUtils.getBaseName(originalFile.getName())
				+ "_" + getFileNameBaseCurrentTimestamp() + "." + FilenameUtils.getExtension(originalFile.getName());
		File newFile = new File(newFilename);
		FileUtils.copyFile(new File(relativePathFilename), newFile);
		return newFile;
	}

	public static String getFileNameBaseCurrentTimestamp() {
		int rndNum = new Random().nextInt(9999);
		return String.format("%04d", rndNum) + System.currentTimeMillis();
	}

	public static MockMultipartFile createMockMultipartFile(String paraName, File file) throws IOException {
		FileInputStream input = new FileInputStream(file);
		MockMultipartFile multipartFile = new MockMultipartFile(paraName, file.getName(), new MimetypesFileTypeMap().getContentType(file.getName()),
				IOUtils.toByteArray(input));
		return multipartFile;
	}

	public static MockMultipartFile createMockMultipartFile(String paraName, String filename) throws IOException {
		return createMockMultipartFile(paraName, new File(filename));
	}
}
