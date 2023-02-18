package pl.wasik.damian.java.app.bank.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UniqueIdentifierGeneratorTest {

    @Test
    void generateAccountNumber_andAddToList() {
        //Given
        List<String> generatedNumbers = new ArrayList<>();
        int numberOfTests = 1000;

        //When
        for (int i = 0; i < numberOfTests; i++) {
            String generatedNumber = UniqueIdentifierGenerator.generateAccountNumber();

            //Then
            Assertions.assertTrue(generatedNumbers.add(generatedNumber), " number is false");
        }
    }

    @Test
    void generateNumber() {
        //Given
        //When
        String firstNumber = UniqueIdentifierGenerator.generateAccountNumber();
        String secondNumber = UniqueIdentifierGenerator.generateAccountNumber();

        //Then
        Assertions.assertNotEquals(firstNumber, secondNumber, " firstNumber is equals secondNumber");
    }

    @Test
    void generateAccountNumber() {
        //Given
        //When
        String generatedNumber = UniqueIdentifierGenerator.generateAccountNumber();

        //Then
        Assertions.assertNotNull(generatedNumber, "generatedNumber is null");
    }
}