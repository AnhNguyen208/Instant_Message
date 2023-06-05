package com.example.instant_message.views;

import com.example.instant_message.controller.UserController;
import com.example.instant_message.entity.User;
import com.example.instant_message.ultils.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FriendScreenHandler extends BaseScreenHandler implements Initializable {
    @FXML
    private Label title;
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem menuItem1;
    @FXML
    private MenuItem menuItem2;
    @FXML
    private MenuItem menuItem3;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;

    public FriendScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    private void displayFriendList() {
        try {
            label.setText("Danh sách bạn bè");
            this.gridPane.getChildren().clear();
            List<User> friendList = ((UserController) baseController).getFriendList(baseController.getUser().getId());
            if(friendList != null) {
                for (User user: friendList) {
                    Integer integer = friendList.indexOf(user);
                    FriendInfoScreenHandler friendInfoController = new FriendInfoScreenHandler(Config.FRIEND_INFO_SCREEN_PATH, this.stage);
                    friendInfoController.setFriendInfo(baseController.getUser(), user);
                    this.gridPane.add(friendInfoController.getContent(), integer / 3, integer % 3);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayFriendSuggestionList() {
        try {
            label.setText("Gợi ý kết bạn");
            this.gridPane.getChildren().clear();
            List<User> friendSuggestionList = ((UserController) baseController).getFriendSuggestionList(baseController.getUser().getId());
            if(friendSuggestionList != null) {
                for (User user: friendSuggestionList) {
                    Integer integer = friendSuggestionList.indexOf(user);
                    FriendInfoScreenHandler friendInfoController = new FriendInfoScreenHandler(Config.FRIEND_INFO_SCREEN_PATH,
                            this.stage);
                    friendInfoController.setFriendSuggestionInfo(baseController.getUser(), user);
                    this.gridPane.add(friendInfoController.getContent(), integer / 3, integer % 3);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayFriendRequestList() {
        try {
            label.setText("Lời mời kết bạn");
            this.gridPane.getChildren().clear();
            List<User> friendRequestList = ((UserController) baseController).getFriendRequestList(baseController.getUser().getId());
            if(friendRequestList != null) {
                for (User user : friendRequestList) {
                    Integer integer = friendRequestList.indexOf(user);
                    FriendInfoScreenHandler friendInfoController = new FriendInfoScreenHandler(Config.FRIEND_INFO_SCREEN_PATH,
                            this.stage);
                    friendInfoController.setFriendRequestInfo(baseController.getUser(), user);
                    this.gridPane.add(friendInfoController.getContent(), integer / 3, integer % 3);
                }
            }
        } catch (IOException | SQLException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        baseController = new UserController();
        title.setText("Bạn bè");
        label.setText("Chọn nội dung bạn muốn xem");
        menuButton.setText("Xin chào, " + baseController.getUser().getName());
        menuItem1.setText("Trang chủ");
        menuItem1.setOnAction(e -> {
            try {
                HomeScreenHandler homeController = new HomeScreenHandler(this.stage, Config.HOME_SCREEN_PATH);
                homeController.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuItem2.setText("Thông tin cá nhân");
        menuItem2.setOnAction(e -> {
            try {
                ProfileScreenHandler profileController = new ProfileScreenHandler(this.stage, Config.PROFILE_SCREEN_PATH);
                profileController.show();
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        });
        menuItem3.setText("Đăng xuất");
        menuItem3.setOnAction(e -> {
            try {
                LoginScreenHandler loginController = new LoginScreenHandler(this.stage, Config.LOGIN_SCREEN_PATH);
                loginController.show();
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        });

        label1.setOnMouseClicked(e-> displayFriendList());
        label2.setOnMouseClicked(e-> displayFriendRequestList());
        label3.setOnMouseClicked(e-> displayFriendSuggestionList());
    }
}
