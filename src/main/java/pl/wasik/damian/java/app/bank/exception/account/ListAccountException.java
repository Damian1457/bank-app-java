package pl.wasik.damian.java.app.bank.exception.account;

import pl.wasik.damian.java.app.bank.exception.AccountException;

public class ListAccountException extends AccountException {
    public ListAccountException(String message) {
        super(message);
    }

    public ListAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
