package pl.wasik.damian.java.app.bank.exception.account;

import pl.wasik.damian.java.app.bank.exception.AccountException;

public class DeleteAccountException extends AccountException {
    public DeleteAccountException(String message) {
        super(message);
    }

    public DeleteAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
