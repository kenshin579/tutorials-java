package com.advenoh.exception;

public enum ExceptionCode {
	SCHEDULER_ADD_FAIL("10000", "스케줄 추가기 문제가 발생했습니다."),
	SCHEDULER_DELETE_FAIL("10010", "스케줄 삭제시 문제가 발생했습니다."),
	SCHEDULER_GET_FAIL("10020", "스케줄 조회시 문제가 발생했습니다");

	private String code;
	private String message;

	ExceptionCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage(String... args) {
		return String.format(this.message, args);
	}

}
