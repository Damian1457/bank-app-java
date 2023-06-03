package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BankIntegrationTest {

    @Test
    void givenBankWithRegisteredClient_whenAddClientToList_thenCheckClientWithList() {
        //Given
        Bank bank = new Bank("BNP Paribas", new Address());
        List<Client> clients = new ArrayList<>();

        //When
        Client damian = bank.registerClient("Damian", "Wąsik", new Address());
        clients.add(damian);
        Client damianW = clients.get(0);

        //Then
        Assertions.assertEquals(damianW, damian, "Client is not equals damian");
    }

    @Test
    void givenBankWithRegisteredClient_whenRetrieveAllClients_thenReturnClientList() {
        //Given
        Bank bank = new Bank("BNP Paribas", new Address());
        List<Client> clients = new ArrayList<>();

        //When
        Client damian = bank.registerClient("Damian", "Wąsik", new Address());
        clients.add(damian);
        List<Client> clientList = bank.allClients();

        //Then
        Assertions.assertNotNull(clientList, "The 'allClients' list is null");
    }
}