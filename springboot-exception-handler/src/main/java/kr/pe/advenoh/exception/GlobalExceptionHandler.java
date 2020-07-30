package kr.pe.advenoh.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<?> handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("[exception] handleException :: uri : {} {} msg : {} {}", request.getRequestURI(), request.getMethod(), ex.getMessage(), ex.getClass(), ex);
        final ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
    }

    @ExceptionHandler({ApiException.class})
    @ResponseBody
    public ResponseEntity<?> handleApiException(HttpServletRequest request, HttpServletResponse response, ApiException ex) {
        log.error("[exception] handleApiException :: uri : {} {} code : {} msg : {} {}", request.getRequestURI(), request.getMethod(), ex.getCode(), ex.getMessage(), ex.getClass(), ex);
        final ErrorResponse error = new ErrorResponse(ex.getHttpStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getHttpStatus(), ex.getMessage(), ex.getCode());
        return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
    }
}
