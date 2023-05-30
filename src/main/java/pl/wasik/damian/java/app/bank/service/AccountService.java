package pl.wasik.damian.java.app.bank.service;

import pl.wasik.damian.java.app.bank.dao.AccountDao;
import pl.wasik.damian.java.app.bank.exception.AccountException;
import pl.wasik.damian.java.app.bank.exception.delete.DeleteAccountException;
import pl.wasik.damian.java.app.bank.exception.read.ReadAccountException;
import pl.wasik.damian.java.app.bank.exception.update.UpdateAccountException;
import pl.wasik.damian.java.app.bank.model.Account;

import java.util.List;
import java.util.logging.Logger;

public class AccountService {
    private static final Logger LOGGER = Logger.getLogger(AccountService.class.getName());
    private AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account create(Account account) throws AccountException {
        LOGGER.info("create(" + account + ")");
        Account createdAccount = accountDao.create(account);
        LOGGER.info("create(...)= " + createdAccount);
        return createdAccount;
    }

    public Account read(int id) throws AccountException {
        LOGGER.info("read(" + id + ")");
        // Optional<Account> readAccountOptional = accountDao.read(id);
        // readAccountOptional.orElseThrow(() -> new ReadAccountException("There is no account with this id number"));

        Account readAccount = accountDao.read(id);
        if (readAccount != null) {
            LOGGER.info("read(...)= " + readAccount);
            return readAccount;
        } else {
            throw new ReadAccountException("There is no account with this id number");
        }
    }

    public Account update(Account account) throws AccountException {
        LOGGER.info("update(" + account + ")");
        Account updatedAccount = accountDao.update(account);
        if (updatedAccount != null) {
            LOGGER.info("update(...)= " + updatedAccount);
            return updatedAccount;
        } else {
            throw new UpdateAccountException("No account to change");
        }
    }

    public int delete(int id) throws AccountException {
        LOGGER.info("delete(" + id + ")");
        int executeDelete = accountDao.delete(id);
        LOGGER.info("delete(...)= " + executeDelete);
        if (executeDelete == 0) {
            throw new DeleteAccountException("There is no account with this id number to delete");
        } else {
            return executeDelete;
        }
    }

    public List<Account> list() throws AccountException {
        LOGGER.info("list()");
        List<Account> accounts = accountDao.list();
        LOGGER.info("list(...)= " + accounts.size() + " accounts");
        return accounts;
    }
}
