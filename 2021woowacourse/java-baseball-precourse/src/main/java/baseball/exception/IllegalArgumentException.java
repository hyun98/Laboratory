package baseball.exception;

public class IllegalArgumentException extends RuntimeException {
    
    protected IllegalArgumentException(final String detail) {
        super(detail);
    }
}
