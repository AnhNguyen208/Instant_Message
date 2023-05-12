package com.example.instant_message.controller;

import com.example.instant_message.db.ConnectDB;
import com.example.instant_message.model.User;
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

    public LoginController(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        BaseController.loginController = this;
    }

    private boolean checkUser() throws SQLException {
        String sql = "SELECT * FROM users WHERE email = '" + email.getText() + "'";
        // System.out.println(sql);
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()) {
            // System.out.println("Email: " + res.getString("email") + " Password: " + res.getString("password"));
            if(!email.getText().equals(res.getString("email"))) {
                return false;
            } else {
                if (!password.getText().equals(res.getString("password")) ) {
                    return false;
                } else {
                    BaseController.user = new User();
                    BaseController.user.setEmail(email.getText());
                    BaseController.user.setName(res.getString("name"));
                    BaseController.user.setPhoneNumber(res.getString("phone_number"));
                    BaseController.user.setDob(res.getDate("dob"));

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setOnMouseClicked( e-> {
            try {
                if(checkUser()) {
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
