package pl.wasik.damian.java.app.bank.model;

public enum TransferStatus {
    OK,
    FAILURE("Nie udało się wykonać przelewu", -1),
    ERROR;

    private String name;
    private int code;

    TransferStatus() {
    }

    TransferStatus(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
