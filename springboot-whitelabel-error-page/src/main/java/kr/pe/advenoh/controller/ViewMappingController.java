package kr.pe.advenoh.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ViewMappingController {
    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("name", "Frank");
        return new ModelAndView("index");
    }
}