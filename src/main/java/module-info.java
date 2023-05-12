module com.example.instant_message {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.instant_message to javafx.fxml;
    exports com.example.instant_message;
    exports com.example.instant_message.controller;
    opens com.example.instant_message.controller to javafx.fxml;
}