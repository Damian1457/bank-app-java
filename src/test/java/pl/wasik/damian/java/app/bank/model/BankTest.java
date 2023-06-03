package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BankTest {

    @Test
    void registerClient() {
        //Given
        Bank bank = new Bank("DamianBank", new Address());

        //When
        Client client = bank.registerClient("Damian", "Wasik", new Address());

        //Then
        Assertions.assertNotNull(client, " Client is null");
    }

    @Test
    void allClients() {
        //Given
        Bank bank = new Bank("DamianBank", new Address());

        //When
        List<Client> clients = bank.allClients();

        //Then
        Assertions.assertNotNull(clients, " Clients is null");
    }
}
