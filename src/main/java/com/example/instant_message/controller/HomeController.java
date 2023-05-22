package com.example.instant_message.controller;

import com.example.instant_message.db.ConnectDB;
import com.example.instant_message.model.User;
import com.example.instant_message.service.UserService;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController extends BaseController implements Initializable {
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem menuItem1;
    @FXML
    private MenuItem menuItem2;
    @FXML
    private VBox vBox;
    private UserService userService;

    public HomeController(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        BaseController.homeController = this;
    }

    private void displayFriendList() throws SQLException {
        List<User> userList = userService.friendList(BaseController.user.getId());
        for (User user1 : userList) {
            HBox hBox = new HBox();
            ImageView imageView = new ImageView(new Image("https://lh3.googleusercontent.com/75wmSt_ybBYi8OSc2JhdNH4iuYFstvkZ3JJxfE3u4b9AODEwmU20qMJkQzvI6WgicFV1xRBrF0sL-9XBaRop9cU2gqtiiTN_mGn_bg1GvP9a2TK8uVutAmy0_ZVlYFlmaeVLl-agyQ=w2400"));
            imageView.setFitHeight(30);
            imageView.setFitWidth(50);
            HBox.setMargin(imageView, new Insets(0, 10, 10, 50));
            Label name = new Label(user1.getName());
            name.setFont(new Font("Arial", 24));
            hBox.getChildren().add(imageView);
            hBox.getChildren().add(name);
            vBox.getChildren().add(hBox);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userService = new UserService();
        menuButton.setText("Xin chào, " + BaseController.user.getName());
        menuItem1.setOnAction(e -> {
            try {
                ProfileController profileController = new ProfileController(this.stage, "/com/example/instant_message/profile.fxml");
                profileController.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuItem2.setOnAction(e -> {
            BaseController.loginController.show();
        });
        try {
            displayFriendList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
