package com.example.instant_message.controller;

import com.example.instant_message.model.User;
import com.example.instant_message.service.UserService;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BaseController extends FXMLController {
    private Scene scene;
    protected final Stage stage;
    protected static User user;
    protected static UserService userService;

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

    public void displayAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText("Thông báo:");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void setUser(Long id, String email, String name, String phoneNumber, String dob) throws ParseException {
        user.setId(id);
        user.setEmail(email);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(dob));
    }
}
