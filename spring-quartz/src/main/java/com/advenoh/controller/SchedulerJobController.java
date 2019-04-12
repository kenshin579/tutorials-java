package com.advenoh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/scheduler")
public class SchedulerJobController {
	@GetMapping("/")
	public void index() {
		log.info("index!!");
	}

	//	@RequestMapping(value = "/live/id3/{mediaNo}", method = RequestMethod.POST)
	//	public Map<String, ?> scheduleForId3Tags(@PathVariable(name = "mediaNo") Long mediaNo) {
	//		Map<String, Object> result = new HashMap<>();
	//		//		boolean scheduleResult = schedulerService.addId3Schedule(mediaNo);
	//		//		result.put("status", scheduleResult);
	//		log.info("[schedulerdebug] creating new schedule for id3 tags :: mediaNo : {}", mediaNo);
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/live/id3/{mediaNo}", method = RequestMethod.DELETE)
	//	public Map<String, ?> deleteScheduleForId3Tags(@PathVariable(name = "mediaNo") Long mediaNo) {
	//		boolean scheduleResult = schedulerService.deleteId3Schedule(mediaNo);
	//
	//		Map<String, Object> result = new HashMap<>();
	//		result.put("status", scheduleResult);
	//		log.info("[schedulerdebug] deleting schedule for id3 tags :: mediaNo : {}", mediaNo);
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	//	public Map<String, ?> getAllJobs() {
	//		List<Map<String, Object>> list = schedulerService.getAllJobs();
	//		Map<String, Object> result = new HashMap<>();
	//		result.put("result", list);
	//		return result;
	//	}
}