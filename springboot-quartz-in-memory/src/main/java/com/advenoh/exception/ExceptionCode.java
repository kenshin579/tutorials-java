package com.advenoh.exception;

public interface ExceptionCode {
    String getCode();

    String getMessage(String... args);
}
