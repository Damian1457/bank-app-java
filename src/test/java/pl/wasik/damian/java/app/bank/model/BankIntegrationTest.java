package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BankIntegrationTest {

//    private Bank bank;

//    @BeforeEach
//    void setUp() {
//        System.out.println("Before test");
//        bank = new Bank("BNP Paribas", new Address());
//    }
//
//    @AfterEach
//    void tearDown() {
//        System.out.println("After test");
//        bank = null;
//    }

    @Test
    void registerClient_whenAddClientToList_thenCheckClientWithList() {
        //Given
        Bank bank = new Bank("BNP Paribas", new Address());
        List<Client> clients = new ArrayList<>();

        //When
        Client damian = bank.registerClient("Damian", "Wąsik", new Address());
        clients.add(damian);
        Client damianW = clients.get(0);

        //Then
        Assertions.assertEquals(damianW, damian, " client is not equals damian");
    }

    @Test
    void allClients() {
        //Given
        Bank bank = new Bank("BNP Paribas", new Address());
        List<Client> clients = new ArrayList<>();

        //When
        Client damian = bank.registerClient("Damian", "Wąsik", new Address());
        clients.add(damian);
        List<Client> clientList = bank.allClients();

        //Then
        Assertions.assertNotNull(clientList, " allClients is null");

    }
}