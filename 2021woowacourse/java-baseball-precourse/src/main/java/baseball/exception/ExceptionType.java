package baseball.exception;

public enum ExceptionType {
    
    USER_INPUT_ILLEGAL_NUMBER("잘못된 값을 입력하였습니다.");
    
    private final String detail;

    ExceptionType(final String detail) {
        this.detail = detail;
    }
}
