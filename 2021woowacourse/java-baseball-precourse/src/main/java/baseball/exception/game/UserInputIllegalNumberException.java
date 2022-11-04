package baseball.exception.game;

import static baseball.exception.ExceptionType.USER_INPUT_ILLEGAL_NUMBER;

public class UserInputIllegalNumberException extends IllegalArgumentException {
    public UserInputIllegalNumberException() {
        super(USER_INPUT_ILLEGAL_NUMBER.name());
    }
}
