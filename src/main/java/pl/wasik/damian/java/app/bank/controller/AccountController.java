package pl.wasik.damian.java.app.bank.controller;

import pl.wasik.damian.java.app.bank.exception.AccountException;
import pl.wasik.damian.java.app.bank.model.Account;
import pl.wasik.damian.java.app.bank.service.AccountService;

import java.util.logging.Logger;

public class AccountController {
    private static final Logger LOGGER = Logger.getLogger(AccountController.class.getName());

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public Account create(Account account) throws AccountException {
        LOGGER.info("create(" + account + ")");

//        account.getNumber();

        LOGGER.info("create(...) = " + null);
        return null;
    }
}
