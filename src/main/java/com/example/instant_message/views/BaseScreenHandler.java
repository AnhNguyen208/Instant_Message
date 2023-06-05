package com.example.instant_message.views;

import com.example.instant_message.controller.BaseController;
import com.example.instant_message.entity.User;
import com.example.instant_message.controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseScreenHandler extends FXMLScreenHandler {
    private Scene scene;
    protected final Stage stage;
    protected static BaseController baseController;

    public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
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

    public void displayAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText("Thông báo:");
        alert.setContentText(contentText);
        alert.showAndWait();
    }


}
