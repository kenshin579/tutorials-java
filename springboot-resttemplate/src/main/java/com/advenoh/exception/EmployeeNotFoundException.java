package kr.pe.advenoh.exception;

public class EmployeeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 5664296823995347411L;

	public EmployeeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
