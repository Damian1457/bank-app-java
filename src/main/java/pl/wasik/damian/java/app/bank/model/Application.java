package pl.wasik.damian.java.app.bank.model;

import javafx.stage.Stage;

import java.io.IOException;

public abstract class Application {
    public static void main(String[] args) {
//        Bank damianBank = new Bank();
//        Account damianAccount = new Account();
//        Account jacekAccount = new Account();
//
//        Account jolaAccount = new Account();
//
//        System.out.println("Jola account before deposit: " + jolaAccount);
//        jolaAccount.deposit(100);
//        System.out.println("Damian account after deposit: " + damianAccount);
//        System.out.println("Jola account before deposit: " + jolaAccount);
//
//        Transfer dinnerTransfer = new Transfer();
////        dinnerTransfer.setAccountFrom(jolaAccount);
////        dinnerTransfer.setAccountTo(damianAccount);
//        //dinnerTransfer.transfer(10);
//        System.out.println("Jola account after deposit: " + jolaAccount);
//        System.out.println("Damian account after deposit: " + damianAccount);
//
////        int transferStatus = dinnerTransfer.dinnerTransfer(jolaAccount, damianAccount, 100.00);
////        System.out.println(transferStatus);
//
////        TransferStatus dinnerTransferStatus = dinnerTransfer.transfer(jacekAccount, damianAccount, 100);
//        TransferStatus dinnerTransferStatus = Transfer.transfer(jacekAccount, damianAccount, 100);
//        System.out.println(dinnerTransferStatus.ordinal());
//        System.out.println(dinnerTransferStatus.getName());
//        System.out.println(dinnerTransferStatus.getCode());
    }

    public abstract void start(Stage stage) throws IOException;
}

