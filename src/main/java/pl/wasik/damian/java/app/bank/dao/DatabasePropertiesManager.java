package pl.wasik.damian.java.app.bank.dao;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabasePropertiesManager {
    private static final Logger LOGGER = Logger.getLogger(DatabasePropertiesManager.class.getName());
    private static DatabasePropertiesManager databasePropertiesManager;
    private Properties properties;

    private DatabasePropertiesManager() {
        LOGGER.info("DatabasePropertiesManager()");
        this.properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("DatabasePropertiesManager(...)");
    }

    public static DatabasePropertiesManager getInstance() {
        LOGGER.info("getInstance()");
        if (databasePropertiesManager == null) {
            databasePropertiesManager = new DatabasePropertiesManager();
        }
        LOGGER.info("getInstance(...)");
        return databasePropertiesManager;
    }

    public String getValue(String key) {
        LOGGER.info("getValue(" + key + ")");
        String property = properties.getProperty(key);
        LOGGER.info("getValue(...) = " + key);
        return property;
    }
}
