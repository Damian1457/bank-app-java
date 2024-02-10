package pl.wasik.damian.java.app.bank.constants.account;

public class AccountDatabaseConstants {
    public static final String CREATE_ACCOUNT = "INSERT INTO ACCOUNTS (ID, ACC_NO, BALANCE) VALUES(?, ?, ?);";
    public static final String READ_ACCOUNT = "SELECT * FROM ACCOUNTS WHERE ID=?";
    public static final String UPDATE_ACCOUNT = "UPDATE ACCOUNTS SET ACC_NO=?, BALANCE=? WHERE ID=?";
    public static final String DELETE_ACCOUNT = "DELETE FROM ACCOUNTS WHERE ID=?";
    public static final String GET_ACCOUNTS_LIST = "SELECT * FROM ACCOUNTS ORDER BY ID;";

    public static final String COLUMN_BALANCE = "BALANCE";
    public static final String COLUMN_ACC_NO = "ACC_NO";
    public static final String COLUMN_ID = "ID";
}
