package pl.wasik.damian.java.app.bank.utils;

import java.util.UUID;

public interface UniqueIdentifierGenerator {
    static String generateAccountNumber() {
        return UUID.randomUUID().toString();
    }
}
