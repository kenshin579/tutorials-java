package com.spring.examples.controller;

import com.spring.examples.model.MediaVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Controller
public class FileUploadController {
	private final String DOWNLOAD_PATH = "/tmp/upload";

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
			file.transferTo(new File(DOWNLOAD_PATH + "/" + "SingleFileUpload", file.getOriginalFilename()));
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

	@PostMapping("/singleFileUploadWithAdditionalData")
	public String singleFileUploadWith(@RequestParam("mediaFile") MultipartFile file,
			@RequestParam final String creator, @RequestParam final String callbackUrl, Model model) throws IOException {

		log.info("creator : {}", creator);
		log.info("callbackUrl : {}", callbackUrl);
		log.info("getOriginalFilename : {}", file.getOriginalFilename());
		log.info("getSize : {}", file.getSize());

		// Save mediaFile on system
		if (!file.getOriginalFilename().isEmpty()) {
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
					new File(DOWNLOAD_PATH + "/" + "SingleFileUploadWithAdditionalData", file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();

			model.addAttribute("msg", "File uploaded successfully.");
		} else {
			model.addAttribute("msg", "Please select a valid mediaFile..");
		}
		return "fileUploadForm";
	}

	@RequestMapping(value = "/uploadFileModelAttribute", method = RequestMethod.POST)
	public String singleFileUploadWith(@ModelAttribute MediaVO mediaVO, Model model) throws IOException {
		MultipartFile file = mediaVO.getMediaFile();

		// Save mediaFile on system
		if (!file.getOriginalFilename().isEmpty()) {
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
					new File(DOWNLOAD_PATH + "/" + "SingleFileUploadWithAdditionalDataWithClassMapping", file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();

//			or
//			file.transferTo(new File(DOWNLOAD_PATH + "/" + "SingleFileUploadWithAdditionalDataWithClassMapping", file.getOriginalFilename()));

			model.addAttribute("msg", "File uploaded successfully.");
		} else {
			model.addAttribute("msg", "Please select a valid mediaFile..");
		}
		return "fileUploadForm";
	}
}
