package com.example.instant_message.controller;

import com.example.instant_message.db.ConnectDB;
import com.example.instant_message.model.User;
import com.example.instant_message.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController extends BaseController implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button btnLogin;

    private UserService userService;

    public LoginController(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        BaseController.loginController = this;
        userService = new UserService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setOnMouseClicked( e-> {
            try {
                if(userService.checkUser(email.getText(), password.getText()) != null) {
                    BaseController.user = userService.checkUser(email.getText(), password.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công");
                    alert.setHeaderText("Thông báo:");
                    alert.setContentText("Đăng nhập thành công");
                    alert.showAndWait();

                    HomeController homeController = new HomeController(this.stage, "/com/example/instant_message/home-page.fxml");
                    homeController.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText("Thông báo:");
                    alert.setContentText("Đăng nhập không thành công");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
