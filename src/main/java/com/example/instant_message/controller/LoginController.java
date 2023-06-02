package com.example.instant_message.controller;

import com.example.instant_message.model.User;
import com.example.instant_message.service.UserService;
import com.example.instant_message.ultils.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController extends BaseController implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button btnLogin;

    public LoginController(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public boolean validateEmailAddress() {
        Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regMatcher   = regexPattern.matcher(email.getText());
        if(!regMatcher.matches()) {
            displayAlert(Alert.AlertType.ERROR, "Lỗi", "Email không hợp lệ");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userService = new UserService();
        btnLogin.setOnMouseClicked( e-> {
            if(validateEmailAddress()) {
                try {
                    User user = userService.checkUser(email.getText(), password.getText());
                    if(user != null) {
                        try {
                            displayAlert(Alert.AlertType.INFORMATION, "Thành công", "Đăng nhập thành công");
                            BaseController.user = user;

                            HomeController homeController = new HomeController(this.stage, Config.HOME_SCREEN_PATH);
                            homeController.show();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        displayAlert(Alert.AlertType.ERROR, "Lỗi", "Email hoặc mật khẩu không chính xác");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
