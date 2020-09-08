package kr.pe.advenoh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestErrorController {

    @GetMapping("/5xx")
    public Object getStudent() {
        int result = 100 / 0;
        return result;
    }
}
