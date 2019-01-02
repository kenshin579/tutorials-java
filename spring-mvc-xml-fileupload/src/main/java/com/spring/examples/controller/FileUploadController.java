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

import static com.spring.examples.controller.Constants.DOWNLOAD_PATH;
import static com.spring.examples.controller.Constants.MULTI_FILE_UPLOAD_PATH;
import static com.spring.examples.controller.Constants.SINGLE_FILE_UPLOAD_AND_EXTRA_DATA1_PATH;
import static com.spring.examples.controller.Constants.SINGLE_FILE_UPLOAD_AND_EXTRA_DATA2_PATH;
import static com.spring.examples.controller.Constants.SINGLE_FILE_UPLOAD_PATH;

@Slf4j
@Controller
public class FileUploadController {
	@GetMapping("/")
	public String fileUploadForm(Model model) {
		return "fileUploadForm";
	}

	@PostMapping("/singleFileUpload")
	public String singleFileUpload(@RequestParam("mediaFile") MultipartFile file, Model model)
			throws IOException {

		// Save mediaFile on system
		if (!file.getOriginalFilename().isEmpty()) {
			file.transferTo(new File(DOWNLOAD_PATH + "/" + SINGLE_FILE_UPLOAD_PATH, file.getOriginalFilename()));
			model.addAttribute("msg", "File uploaded successfully.");
		} else {
			model.addAttribute("msg", "Please select a valid mediaFile..");
		}

		return "fileUploadForm";
	}

	@PostMapping("/multipleFileUpload")
	public String multipleFileUpload(@RequestParam("mediaFile") MultipartFile[] files,
			Model model) throws IOException {

		// Save mediaFile on system
		for (MultipartFile file : files) {
			if (!file.getOriginalFilename().isEmpty()) {
				BufferedOutputStream outputStream = new BufferedOutputStream(
						new FileOutputStream(
								new File(DOWNLOAD_PATH + "/" + MULTI_FILE_UPLOAD_PATH, file.getOriginalFilename())));

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

		// Save mediaFile on system
		if (!file.getOriginalFilename().isEmpty()) {
			file.transferTo(new File(DOWNLOAD_PATH + "/" + SINGLE_FILE_UPLOAD_AND_EXTRA_DATA1_PATH, file.getOriginalFilename()));
			model.addAttribute("msg", "File uploaded successfully.");
		} else {
			model.addAttribute("msg", "Please select a valid mediaFile..");
		}
		return "fileUploadForm";
	}

	@RequestMapping(value = "/uploadFileModelAttribute", method = RequestMethod.POST)
	public String singleFileUploadWith(@ModelAttribute MediaVO mediaVO, Model model) throws IOException {
		MultipartFile file = mediaVO.getMediaFile();

		log.info("mediaVO: {}", mediaVO);

		// Save mediaFile on system
		if (!file.getOriginalFilename().isEmpty()) {
			file.transferTo(new File(DOWNLOAD_PATH + "/" + SINGLE_FILE_UPLOAD_AND_EXTRA_DATA2_PATH, file.getOriginalFilename()));

			model.addAttribute("msg", "File uploaded successfully.");
		} else {
			model.addAttribute("msg", "Please select a valid mediaFile..");
		}
		return "fileUploadForm";
	}


}
