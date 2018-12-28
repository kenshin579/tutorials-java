package com.boraji.tutorial.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * @author imssbora
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	private static int MAX_FILE_ZIZE = 10 * 1024 * 1024;

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {

		//Parameters:-
		//   location - the directory location where files will be stored
		//   maxFileSize - the maximum size allowed for uploaded files
		//   maxRequestSize - the maximum size allowed for multipart/form-data requests
		//   fileSizeThreshold - the size threshold after which files will be written to disk
		MultipartConfigElement multipartConfig = new MultipartConfigElement("/tmp/upload", MAX_FILE_ZIZE,
				MAX_FILE_ZIZE, 0);
		registration.setMultipartConfig(multipartConfig);
	}
}
