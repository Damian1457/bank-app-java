package pl.wasik.damian.java.app.bank.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatabasePropertiesManagerTest {

    @Test
    void getValue() {
        //Given
        //When
        String value = DatabasePropertiesManager.getInstance().getValue("test.key");

        //Then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(value, "Value is equal null"),
                () -> Assertions.assertEquals("test_value", value, "Values isn't equals")
        );
    }
}