package com.java.examples;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JEP286Test {
    final String filePath = "com/java/examples/test.txt";

    @Test
    public void test_JEP286_적용전_버전() throws URISyntaxException {
        Path path = Paths.get(this.getClass().getClassLoader().getResource(filePath).toURI());
        System.out.println("path" + path);
        try (Stream<String> lines = Files.lines(path)) {
            long warningCount
                    = lines
                    .filter(line -> line.contains("WARNING"))
                    .count();
            System.out.println("Found " + warningCount + " warnings in the log file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_JEP286() throws URISyntaxException {
        Path path = Paths.get(this.getClass().getClassLoader().getResource(filePath).toURI());
        try (var lines = Files.lines(path)) {
            var warningCount
                    = lines
                    .filter(line -> line.contains("WARNING"))
                    .count();
            System.out.println("Found " + warningCount + " warnings in the log file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}