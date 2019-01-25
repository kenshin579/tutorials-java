package com.java.examples;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class PrePostTest {
    private static Logger LOG = LoggerFactory.getLogger(PrePostTest.class);

    @Test
    public void testWriteLineToFileWrapper() throws IOException {
        writeLineToFile("output.txt", "this is a test", (printWriter, line) -> {
            printWriter.print(line);
        });
    }

    @Test
    public void testExecuteAndResetConstantValue() throws Exception {
        int newId = 3;

        //내부적으로 Constant값을 3으로 세팅했다가 기존값으로 원복시킴
        executeAndResetConstantValue(newId, () -> {
            LOG.info("Constants: {}", Constants.SIZE);
        });
    }

    @Test
    public void testExecuteAndResetConstantValueWithRunnable() throws Exception {
        int newId = 5;

        //내부적으로 Constant값을 3으로 세팅했다가 기존값으로 원복시킴
        executeAndPrePostProcessWithRunnable(newId, () -> {
            LOG.info("Constants: {}", Constants.SIZE);
        });
    }

    /**
     * https://dzone.com/articles/how-to-change-private-static-final-fields
     *
     * @param clazz
     * @param fieldName
     * @param value
     * @throws ReflectiveOperationException
     */
    private static void setFinalStaticIntField(Class<?> clazz, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, value);
    }

    private interface Task {
        void execute();
    }

    private static void executeAndResetConstantValue(int newSize, Task task) throws NoSuchFieldException, IllegalAccessException {
        int originalSize = Constants.SIZE;
        //pre 처리 - 인자 값으로 세팅함
        setFinalStaticIntField(Constants.class, "SIZE", newSize);
        LOG.info("pre 처리 - SIZE: {}", Constants.SIZE);

        //실행
        task.execute();

        //post 처리 - 기존 값으로 원복함
        setFinalStaticIntField(Constants.class, "SIZE", originalSize);
        LOG.info("post 처리 - SIZE: {}", Constants.SIZE);
    }

    private static void executeAndPrePostProcessWithRunnable(int newSize, Runnable r) throws NoSuchFieldException, IllegalAccessException {
        int originalSize = Constants.SIZE;
        //pre 처리 - 인자 값으로 세팅함
        setFinalStaticIntField(Constants.class, "SIZE", newSize);
        LOG.info("pre 처리 - SIZE: {}", Constants.SIZE);

        //실행
        r.run();

        //post 처리 - 기존 값으로 원복함
        setFinalStaticIntField(Constants.class, "SIZE", originalSize);
        LOG.info("post 처리 - SIZE: {}", Constants.SIZE);
    }

    private interface FileWrite {
        void writeToFile(PrintWriter pw, String line);
    }

    private static void writeLineToFile(final String fileName, String line, FileWrite fileWrite) throws IOException {
        LOG.info("pre 처리 : open file");
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        fileWrite.writeToFile(printWriter, line);

        LOG.info("post 처리 : close file");
        printWriter.close();
    }
}
