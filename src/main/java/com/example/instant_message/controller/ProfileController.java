package com.example.instant_message.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController extends BaseController implements Initializable {
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem menuItem1;
    @FXML
    private MenuItem menuItem2;
    @FXML
    private Label name;
    @FXML
    private Label dob;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label email;

    public ProfileController(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuButton.setText("Xin chào, " + BaseController.user.getName());
        menuItem1.setOnAction(e -> {
            BaseController.homeController.show();
        });
        menuItem2.setOnAction(e -> {
            BaseController.loginController.show();
        });
        name.setText(BaseController.user.getName());
        //System.out.println(BaseController.user.getName());

        email.setText(BaseController.user.getEmail());
        //System.out.println(BaseController.user.getEmail());

        phoneNumber.setText(BaseController.user.getPhoneNumber());

        dob.setText(BaseController.user.getDob().toString());
    }
}
