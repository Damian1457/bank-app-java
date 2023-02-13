package pl.wasik.damian.java.app.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    //    private String street;
//    private String houseNumber;
//    private String postalCode;
    private Address address; //
    private List<Client> clients = new ArrayList<>(); // clients - zależność.

    public Bank(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Client registerClient(String firstName, String lastName, Address address) {
        Client client = new Client(firstName, lastName, address);
        clients.add(client);

        return client;
    }

    public List<Client> allClients() {
        return clients;
    }
}
