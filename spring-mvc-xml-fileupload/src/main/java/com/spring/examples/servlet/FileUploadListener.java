package com.spring.examples.servlet;

import com.spring.examples.controller.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileSystemUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@Slf4j
@WebListener
public class FileUploadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.debug("FileUploadListener started");
        resetTempFolder();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("FileUploadListener ended");
        deleteTempFolder();
    }

    private void deleteTempFolder() {
        log.info("deleting temp folder");
        FileSystemUtils.deleteRecursively(new File(Constants.DOWNLOAD_PATH));
    }

    private void resetTempFolder() {
        log.info("reseting temp folder");
        FileSystemUtils.deleteRecursively(new File(Constants.DOWNLOAD_PATH));

        File newPath;
        for (String folder : Constants.UPLOAD_FOLDER_LIST) {
            newPath = new File(Constants.DOWNLOAD_PATH + "/" + folder);
            log.info("creating... {}", newPath);
            newPath.mkdirs();
        }
    }
}


