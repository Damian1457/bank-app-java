package pl.wasik.damian.java.app.bank.utils;

import java.util.UUID;

public interface UniqueIdentifierGenerator {
    static String generateAccountNumber() {
        return UUID.randomUUID().toString();
    }
    // TODO: 03.02.2023 Dopisać test jednostkowy dla metody powyżej.
}
