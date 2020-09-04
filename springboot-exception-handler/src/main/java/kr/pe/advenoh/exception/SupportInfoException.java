package kr.pe.advenoh.exception;

public class SupportInfoException extends RuntimeException {
    private static final long serialVersionUID = -6234353119945998503L;

    public SupportInfoException(String message) {
        super(message);
    }

    public SupportInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
