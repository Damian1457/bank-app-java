package pl.wasik.damian.java.app.bank.model;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Bank {
    private static final Logger LOGGER = Logger.getLogger(Bank.class.getName());
    private String name;
    private Address address;
    private List<Client> clients = new ArrayList<>();

    public Bank(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Client registerClient(String firstName, String lastName, Address address) {
        LOGGER.info("registerClient(" + firstName + ", " + lastName + ", " + address + ")");
        Client client = new Client(firstName, lastName, address);
        clients.add(client);
        LOGGER.info("registerClient(...) = " + client);
        return client;
    }

    public List<Client> allClients() {
        LOGGER.info("allClients()");
        LOGGER.info("AllClients(...) = " + clients);
        return clients;
    }

    @Override
    public String toString() {
        return "Bank{" + "name='" + name + '\'' + ", address=" + address + '}';
    }
}
