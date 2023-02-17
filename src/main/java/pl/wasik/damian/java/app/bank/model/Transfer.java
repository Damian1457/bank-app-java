package pl.wasik.damian.java.app.bank.model;

public class Transfer {
//    private Account accountFrom;
//    private Account accountTo;

//    public void transfer(double amount) {
////       accountFrom.withdraw(amount);
////       accountTo.deposit(amount);
//    }

    public static TransferStatus transfer(Account accountFrom, Account accountTo, double amount) {
        double accountFromBalance = accountFrom.balance();
        if (accountFromBalance < amount) {
            return TransferStatus.FAILURE;
//            return BankApplicationConstants.TRANSFER_STATUS_FAILURE;
        } else {
            accountFrom.withdraw(amount);
            accountTo.deposit(amount);
            return TransferStatus.OK;
//            return BankApplicationConstants.TRANSFER_STATUS_OK;
        }
        //return 0;
    }

//    public Account getAccountFrom() {
//        return accountFrom;
//    }
//
//    public void setAccountFrom(Account accountFrom) {
//        this.accountFrom = accountFrom;
//    }
//
//    public Account getAccountTo() {
//        return accountTo;
//    }
//
//    public void setAccountTo(Account accountTo) {
//        this.accountTo = accountTo;
//    }
}
