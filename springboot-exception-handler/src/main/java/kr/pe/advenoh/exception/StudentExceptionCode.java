package kr.pe.advenoh.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StudentExceptionCode implements ExceptionCode {
    /*
    student     : 10000
    */

    STUDENT_NOT_FOUND("10000", "요청하신 학생은 존재하지 않습니다"),
    STUDENT_REQUEST_INVALID("10010", "요청하신 값이 잘못 되었습니다. 입력 값을 확인해주세요"),
    ;

    private String code;
    private String message;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage(String... args) {
        return String.format(this.message, args);
    }
}
