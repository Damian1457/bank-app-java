package pl.wasik.damian.java.app.bank.service;

import pl.wasik.damian.java.app.bank.dao.AccountDao;
import pl.wasik.damian.java.app.bank.exception.AccountException;
import pl.wasik.damian.java.app.bank.exception.account.ReadAccountException;
import pl.wasik.damian.java.app.bank.exception.account.UpdateAccountException;
import pl.wasik.damian.java.app.bank.model.Account;

import java.util.List;
import java.util.Optional;
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
        Optional<Account> readAccountOptional = accountDao.read(id);
        Account account = readAccountOptional.orElseThrow(() -> new ReadAccountException("There is no account with this id number"));
        LOGGER.info("read(...)= " + account);
        return account;
    }

    public Account update(Account account) throws AccountException {
        LOGGER.info("update(" + account + ")");
        Optional<Account> updatedAccountOptional = accountDao.update(account);
        Account updatedAccount = updatedAccountOptional.orElseThrow(() -> new UpdateAccountException("No account to change"));
        LOGGER.info("update(...)= " + updatedAccount);
        return updatedAccount;
    }

    public void delete(int id) throws AccountException {
        LOGGER.info("delete(" + id + ")");
        accountDao.delete(id);
        LOGGER.info("delete(...)");
    }

    public List<Account> list() throws AccountException {
        LOGGER.info("list()");
        List<Account> accounts = accountDao.list();
        LOGGER.info("list(...)= " + accounts.size() + " accounts");
        return accounts;
    }
}
