package com.mybatis.sample.controller;

import com.mybatis.sample.service.CodeService;
import com.mybatis.sample.vo.CodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class CodeController {

	@Autowired
	private CodeService codeService;

	@RequestMapping(value = "/code/list")
	public ModelAndView getCodeList(@ModelAttribute CodeVO vo) throws Exception {

		log.info(vo.toString().replaceAll("null", ""));

		ModelAndView mv = new ModelAndView();

		// 목록조회
		List<Map<String, Object>> list = codeService.getCodeList(vo);

		// View에 보여줄 데이터 SET
		mv.addObject("list", list);

		// View 이름 설정
		mv.setViewName("result");

		return mv;
	}

	@RequestMapping(value = "/code/save")
	public ModelAndView saveCode(@ModelAttribute CodeVO vo) throws Exception {

		log.info(vo.toString().replaceAll("null", ""));

		ModelAndView mv = new ModelAndView();

		// 데이터 저장
		int result = codeService.saveCode(vo);

		// 저장결과 SET
		mv.addObject("result", result);

		// View 이름 설정
		mv.setViewName("result");

		return mv;
	}

	@RequestMapping(value = "/code/remove")
	public ModelAndView removeCode(@ModelAttribute CodeVO vo) throws Exception {

		log.info(vo.toString().replaceAll("null", ""));

		ModelAndView mv = new ModelAndView();

		// 데이터 삭제
		int result = codeService.removeCode(vo);

		// 삭제결과 SET
		mv.addObject("result", result);

		// View 이름 설정
		mv.setViewName("result");

		return mv;
	}
}
