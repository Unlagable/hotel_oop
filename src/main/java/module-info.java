module com.example.hms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.hms to javafx.fxml;
    exports com.example.hms;
}