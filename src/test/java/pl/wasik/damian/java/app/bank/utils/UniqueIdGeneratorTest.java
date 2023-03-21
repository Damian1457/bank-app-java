package pl.wasik.damian.java.app.bank.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueIdGeneratorTest {

    @Test
    void generateId() {
        //Given
        //When
        int firstGeneratedId = UniqueIdGenerator.generateId();
        int secondGeneratedId = UniqueIdGenerator.generateId();

        //Then
        Assertions.assertNotEquals(firstGeneratedId, secondGeneratedId, " generated id's are equals");
    }
}