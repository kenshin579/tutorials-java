package kr.pe.advenoh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewMappingController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
