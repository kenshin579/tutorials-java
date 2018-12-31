package com.spring.examples.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MediaVO {
	private String creator;
	private String callbackUrl;
	private MultipartFile mediaFile;
}
