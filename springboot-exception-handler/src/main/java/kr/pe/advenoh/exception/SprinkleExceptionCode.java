package kr.pe.advenoh.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SprinkleExceptionCode implements ExceptionCode {
    /*
    system      : 10000
    */

    TOKEN_EXPIRED("10000", "토큰이 유효하지 않습니다"),
    TOKEN_NOT_FOUND("10010", "존재하지 않는 토큰입니다"),
    TOKEN_EXPIRED_FOR_KMONEY_QUERY("10020", "최대 조회시간이 지나서 정보를 조회할 수 없습니다"),
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
