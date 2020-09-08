package kr.pe.advenoh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errors/404-custom";
            }
        }
        return "error";
    }

    /**
     * 이 메서드는 스프링 부트 2.3.x부터 deprecated 됨
     * - 이 메서드 대신 custom path를 지정하려면 server.error.path 속성으로 지정해야 한다
     */
    @Override
    public String getErrorPath() {
        return null;
    }
}
