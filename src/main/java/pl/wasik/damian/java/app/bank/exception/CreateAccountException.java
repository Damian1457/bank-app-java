package pl.wasik.damian.java.app.bank.exception;

public class CreateAccountException extends AccountException {
    public CreateAccountException(String message) {
        super(message);
    }

    public CreateAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
