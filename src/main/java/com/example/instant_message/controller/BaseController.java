package com.example.instant_message.controller;

import com.example.instant_message.model.User;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class BaseController extends FXMLController {
    private Scene scene;
    protected final Stage stage;
    protected static User user;
    protected static LoginController loginController;
    protected static HomeController homeController;

    public BaseController(Stage stage, String screenPath) throws IOException {
        super(screenPath);
        this.stage = stage;
    }

    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.content);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void setScreenTitle(String string) {
        this.stage.setTitle(string);
    }

    public void displayAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText("Thông báo:");
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
