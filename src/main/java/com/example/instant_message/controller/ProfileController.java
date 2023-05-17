package com.example.instant_message.controller;

import com.example.instant_message.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ProfileController extends BaseController implements Initializable {
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem menuItem1;
    @FXML
    private MenuItem menuItem2;
    @FXML
    private TextField name;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private Button btnEdit;
    private UserService userService;

    public ProfileController(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        userService = new UserService();
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
        name.setEditable(false);
        //System.out.println(BaseController.user.getName());

        email.setText(BaseController.user.getEmail());
        email.setEditable(false);
        //System.out.println(BaseController.user.getEmail());

        phoneNumber.setText(BaseController.user.getPhoneNumber());
        phoneNumber.setEditable(false);

        dob.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(BaseController.user.getDob())));
        dob.getEditor().setEditable(false);

        btnEdit.setOnMouseClicked(e-> {
            if(btnEdit.getText().equals("Chỉnh sửa thông tin")) {
                name.setEditable(true);
                email.setEditable(true);
                phoneNumber.setEditable(true);
                dob.setEditable(true);
                btnEdit.setText("Xác nhận");
            } else {
                try {
                    userService.updateUser(BaseController.user.getId(), email.getText(), name.getText(), phoneNumber.getText(), dob.getValue(). format(DateTimeFormatter. ofPattern("yyyy-MM-dd")));
                    name.setEditable(false);
                    email.setEditable(false);
                    phoneNumber.setEditable(false);
                    dob.setEditable(false);
                    btnEdit.setText("Chỉnh sửa thông tin");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
