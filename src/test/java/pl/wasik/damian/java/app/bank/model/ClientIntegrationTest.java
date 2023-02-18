package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.utils.UniqueIdentifierGenerator;

class ClientIntegrationTest {

    @Test
    void openAccountUniqueNumberTest() {
        //Given
        //When
        String uniqueCodeOne =  UniqueIdentifierGenerator.generateAccountNumber();
        String uniqueCodeTwo =  UniqueIdentifierGenerator.generateAccountNumber();

        //Then
        Assertions.assertNotEquals(uniqueCodeOne, uniqueCodeTwo, " uniqueCodeOne is equals uniqueCodeTwo");
    }
}