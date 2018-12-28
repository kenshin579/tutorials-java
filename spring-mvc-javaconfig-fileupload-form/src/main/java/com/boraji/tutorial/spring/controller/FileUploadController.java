package com.boraji.tutorial.spring.controller;

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
	private final String DOWNLOAD_PATH = "/Users/frankyoh/Desktop/upload";

	@GetMapping("/")
	public String fileUploadForm(Model model) {

		return "fileUploadForm";
	}

	// Handling single mediaFile upload request
	@PostMapping("/singleFileUpload")
	public String singleFileUpload(@RequestParam("mediaFile") MultipartFile file, Model model)
			throws IOException {

		// Save mediaFile on system
		if (!file.getOriginalFilename().isEmpty()) {
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
					new File(DOWNLOAD_PATH + "/" + "SingleFileUpload", file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();

			model.addAttribute("msg", "File uploaded successfully.");
		} else {
			model.addAttribute("msg", "Please select a valid mediaFile..");
		}

		return "fileUploadForm";
	}

	// Handling multiple files upload request
	@PostMapping("/multipleFileUpload")
	public String multipleFileUpload(@RequestParam("mediaFile") MultipartFile[] files,
			Model model) throws IOException {

		// Save mediaFile on system
		for (MultipartFile file : files) {
			if (!file.getOriginalFilename().isEmpty()) {
				BufferedOutputStream outputStream = new BufferedOutputStream(
						new FileOutputStream(
								new File(DOWNLOAD_PATH + "/" + "MultipleFileUpload", file.getOriginalFilename())));

				outputStream.write(file.getBytes());
				outputStream.flush();
				outputStream.close();
			} else {
				model.addAttribute("msg", "Please select at least one mediaFile..");
				return "fileUploadForm";
			}
		}
		model.addAttribute("msg", "Multiple files uploaded successfully.");
		return "fileUploadForm";
	}
}
