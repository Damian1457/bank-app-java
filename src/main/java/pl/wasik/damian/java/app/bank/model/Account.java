package pl.wasik.damian.java.app.bank.model;

public class Account {
    private String number;
//    private double balance = 10.0;
    private double balance;

    public Account(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public double withdraw(double amount) {
        //this.balance = this.balance - amount;
        if (amount <= this.balance) {
            this.balance -= amount;
        }
//        else {
//            System.out.println(TransferStatus.FAILURE + " because you don't have money!");
//        }
        return this.balance;
    }

    public double deposit(double amount) {
        balance += amount;
        return this.balance;
    }

    public double balance() {
        return this.balance;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Account{" +
//                "number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }
}
