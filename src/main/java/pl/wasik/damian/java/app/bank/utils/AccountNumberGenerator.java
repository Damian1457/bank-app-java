package pl.wasik.damian.java.app.bank.utils;

import java.util.UUID;
import java.util.logging.Logger;

public interface AccountNumberGenerator {
    Logger LOGGER = Logger.getLogger(AccountNumberGenerator.class.getName());

    static String generate() {
        LOGGER.info("generate()");
        String accountNumber = UUID.randomUUID().toString();
        LOGGER.info("generate(...) = " + accountNumber);
        return accountNumber;
    }
}
