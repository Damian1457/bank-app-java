package pl.wasik.damian.java.app.bank.exception.account;

import pl.wasik.damian.java.app.bank.exception.AccountException;

public class ReadAccountException extends AccountException {
    public ReadAccountException(String message) {
        super(message);
    }

    public ReadAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
