package pl.wasik.damian.java.app.bank.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Bank {
    private static final Logger LOGGER = Logger.getLogger(Bank.class.getName());

    private String name;
    //    private String street;
//    private String houseNumber;
//    private String postalCode;
    private Address address;
    private List<Client> clients = new ArrayList<>();

    public Bank(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Client registerClient(String firstName, String lastName, Address address) {
//        System.out.println("registerClient(" + firstName + ", " + lastName + ", " + address + ")");
        LOGGER.info("registerClient(" + firstName + ", " + lastName + ", " + address + ")");
        Client client = new Client(firstName, lastName, address);
        clients.add(client);

        LOGGER.info("registerClient(...) = " + client);
//        System.out.println("registerClient(...) = " + client);
        return client;
    }

    public List<Client> allClients() { //Zrobić loggera
        return clients;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

// TODO: 28.02.2023 Dla wszystkich metod publicznych dostępnych w ramach aplikacji bankowej dodać loggery na wejściu i wyjściu.  
