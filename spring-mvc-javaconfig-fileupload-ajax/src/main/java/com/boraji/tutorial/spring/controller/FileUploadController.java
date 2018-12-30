package com.boraji.tutorial.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author imssbora
 */
@Controller
public class FileUploadController {

	@GetMapping("/")
	public String fileUploadForm(Model model) {
		return "fileUploadForm";
	}

	// Handling mediaFile upload request
	@PostMapping("/fileUpload")
	public ResponseEntity<Object> fileUpload(@RequestParam("mediaFile") MultipartFile file)
			throws IOException {

		// Save mediaFile on system
		if (!file.getOriginalFilename().isEmpty()) {
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(
							new File("/tmp/upload", file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();
		} else {
			return new ResponseEntity<>("Invalid mediaFile.", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("File Uploaded Successfully.", HttpStatus.OK);
	}
}
