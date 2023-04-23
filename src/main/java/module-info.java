module pl.wasik.damian.java.app.bank.gui.javafx {
    requires java.base;
    requires java.sql;

    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;

    requires org.controlsfx.controls;

    opens pl.wasik.damian.java.app.bank.gui.javafx to javafx.fxml;
    exports pl.wasik.damian.java.app.bank.gui.javafx;
}