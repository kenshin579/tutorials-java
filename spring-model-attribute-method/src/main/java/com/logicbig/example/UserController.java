package com.logicbig.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Controller
@RequestMapping("users")
public class UserController {
	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

	private static int counter = 0;

	@RequestMapping
	public String handleRequest(Model model) {
		model.addAttribute("msg", "user request received");
		LOGGER.info(model.toString());
		return "my-page";
	}

	@RequestMapping("{id}")
	public String handleRequestById(@PathVariable("id") String id, Model model) {
		model.addAttribute("msg", "user request received for the id : " + id);
		LOGGER.info(model.toString());
		return "my-page";
	}

	@ModelAttribute("time")
	public LocalDateTime getRequestTime() {
		return LocalDateTime.now();
	}

	@ModelAttribute("visits")
	public int getRequestCount() {
		return ++counter;
	}

	@ModelAttribute("querier")
	public void populateIds(@RequestParam(value = "querier", required = false) String querier, Model model) {
		model.addAttribute("querier", querier == null ? "quest" : querier);
	}
}