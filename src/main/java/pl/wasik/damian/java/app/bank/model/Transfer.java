package pl.wasik.damian.java.app.bank.model;

import pl.wasik.damian.java.app.bank.exception.AccountException;

import java.util.logging.Logger;

public class Transfer {

    private static final Logger LOGGER = Logger.getLogger(Transfer.class.getName());

    public static TransferStatus transfer(Account accountFrom, Account accountTo, double amount) throws AccountException {
        LOGGER.info("transfer(" + accountFrom + ", " + accountTo + ", " + amount + ")");

        double accountFromBalance = accountFrom.balance();
        if (accountFromBalance < amount) {
            LOGGER.info("transfer(...) = " + TransferStatus.FAILURE);
            return TransferStatus.FAILURE;
        } else {
            accountFrom.withdraw(amount);
            accountTo.deposit(amount);
            LOGGER.info("transfer(...) = " + TransferStatus.OK);
            return TransferStatus.OK;
        }
    }
}
