package pl.wasik.damian.java.app.bank.service;

import pl.wasik.damian.java.app.bank.dao.AddressDao;
import pl.wasik.damian.java.app.bank.exception.AccountException;
import pl.wasik.damian.java.app.bank.model.Address;

import java.util.logging.Logger;

public class AddressService {
    private static final Logger LOGGER = Logger.getLogger(AddressService.class.getName());
    private AddressDao addressDao;

    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public Address create(Address address) throws AccountException {
        LOGGER.info("create(" + address + ")");
        Address createdAddress = addressDao.create(address);
        LOGGER.info("create(...) = " + createdAddress);
        return createdAddress;
    }
}
