package com.example.instant_message.controller;

import com.example.instant_message.model.User;
import com.example.instant_message.ultils.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController extends BaseController implements Initializable {
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
    private VBox vBox;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Label labelName;
    @FXML
    private Button btnCreateGroup;

    public HomeController(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    private void displayFriendList(List<User> userList) {
        vBox.getChildren().clear();
        for (User user1 : userList) {
            HBox hBox = new HBox();
            ImageView imageView = new ImageView(new Image("https://lh3.googleusercontent.com/75wmSt_ybBYi8OSc2JhdNH4i" +
                    "uYFstvkZ3JJxfE3u4b9AODEwmU20qMJkQzvI6WgicFV1xRBrF0sL-9XBaRop9cU2gqtiiTN_mGn_bg1GvP9a2TK8uVutAmy0_ZV" +
                    "lYFlmaeVLl-agyQ=w2400"));
            imageView.setFitHeight(30);
            imageView.setFitWidth(50);
            HBox.setMargin(imageView, new Insets(0, 10, 10, 50));
            Label name = new Label(user1.getName());
            name.setFont(new Font("Arial", 24));
            name.setOnMouseClicked(e -> {
                labelName.setText(name.getText());
            });
            hBox.getChildren().add(imageView);
            hBox.getChildren().add(name);
            vBox.getChildren().add(hBox);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            labelName.setText("Chọn 1 người bạn hoặc 1 nhóm chat");
            title.setText("Trang chủ");
            menuButton.setText("Xin chào, " + BaseController.user.getName());
            List <User> friendList = userService.friendList(BaseController.user.getId());
            if(friendList != null) {
                displayFriendList(friendList);
            }

            menuItem1.setOnAction(e -> {
                try {
                    ProfileController profileController = new ProfileController(this.stage, Config.PROFILE_SCREEN_PATH);
                    profileController.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            menuItem2.setOnAction(e -> {
                try {
                    FriendController friendController = new FriendController(this.stage, Config.FRIEND_SCREEN_PATH);
                    friendController.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            menuItem3.setOnAction(e -> {
                try {
                    LoginController loginController = new LoginController(this.stage, Config.LOGIN_SCREEN_PATH);
                    loginController.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnSearch.setOnMouseClicked(e -> {
                String find = textFieldSearch.getText();
                List<User> resultSearchList = new ArrayList<>();
                for (User user : friendList) {
                    if(user.getName().toLowerCase().contains(find.toLowerCase())) {
                        resultSearchList.add(user);
                    }
                }
                displayFriendList(resultSearchList);
            });
            btnCreateGroup.setOnMouseClicked(e -> {
                try {
                    CreateGroupController createGroupController = new CreateGroupController(new Stage(), Config.CREATE_GROUP_SCREEN_PATH);
                    createGroupController.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
