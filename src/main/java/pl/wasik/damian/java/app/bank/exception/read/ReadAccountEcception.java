package pl.wasik.damian.java.app.bank.exception.read;

import pl.wasik.damian.java.app.bank.exception.AccountException;

public class ReadAccountEcception extends AccountException {
    public ReadAccountEcception(String message) {
        super(message);
    }

    public ReadAccountEcception(String message, Throwable cause) {
        super(message, cause);
    }
}
