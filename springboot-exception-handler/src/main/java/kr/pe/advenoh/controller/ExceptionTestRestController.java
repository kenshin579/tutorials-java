package kr.pe.advenoh.controller;

import kr.pe.advenoh.exception.SupportInfoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/exception")
public class ExceptionTestRestController {
    @GetMapping("/supportInfoException")
    public Object throwCustomException() {
        log.debug("Throw SupportInfoException");
        throw new SupportInfoException("Exception occurred");
    }
}
