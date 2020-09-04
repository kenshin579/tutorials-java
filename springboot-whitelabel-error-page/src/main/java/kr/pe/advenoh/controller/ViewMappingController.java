package kr.pe.advenoh.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ViewMappingController {
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        return "index";
    }
}