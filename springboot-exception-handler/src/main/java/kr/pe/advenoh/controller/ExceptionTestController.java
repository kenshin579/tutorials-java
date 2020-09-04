package kr.pe.advenoh.controller;

import kr.pe.advenoh.exception.SupportInfoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/api/exception2")
public class ExceptionTestController {
    @GetMapping("/supportInfoException")
    public String throwCustomException(Model model) {
        log.debug("Throw SupportInfoException");
        if (true) {
            throw new SupportInfoException("Exception occurred");
        }
        return "index";
    }
}
