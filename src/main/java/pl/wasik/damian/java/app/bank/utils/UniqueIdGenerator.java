package pl.wasik.damian.java.app.bank.utils;

import java.util.logging.Logger;

public class UniqueIdGenerator {

    private static final Logger LOGGER = Logger.getLogger(UniqueIdGenerator.class.getName());
    private static int idCounter = 0;

    public static synchronized int generateId() {
        // FIXME generowanie identyfikatorów po stronie bazy danych za pomocą sekwencji.
        // Co to jest sekwencja w bazie danych?
        // Jak użyć sekwencji w kodzie Java?

        ++idCounter;
        LOGGER.info("generateId() = " + idCounter);
        return idCounter;
    }
}
