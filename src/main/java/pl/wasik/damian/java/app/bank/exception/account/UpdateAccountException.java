package pl.wasik.damian.java.app.bank.exception.account;

import pl.wasik.damian.java.app.bank.exception.AccountException;

public class UpdateAccountException extends AccountException {
    public UpdateAccountException(String message) {
        super(message);
    }

    public UpdateAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
