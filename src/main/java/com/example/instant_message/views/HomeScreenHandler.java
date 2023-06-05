package com.example.instant_message.views;

import com.example.instant_message.controller.GroupController;
import com.example.instant_message.controller.UserController;
import com.example.instant_message.entity.Group;
import com.example.instant_message.entity.User;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {
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

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    private void displayFriendListAndGroupList(List<User> userList, List<Group> groupList) {
        vBox.getChildren().clear();
        for (User user1 : userList) {
            HBox hBox = new HBox();
            File file = new File("images/user.png");
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
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

        for (Group group : groupList) {
            HBox hBox = new HBox();
            File file = new File("images/people.png");
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitHeight(30);
            imageView.setFitWidth(50);
            HBox.setMargin(imageView, new Insets(0, 10, 10, 50));
            Label name = new Label(group.getGroupName());
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
            baseController = new UserController();
            labelName.setText("Chọn 1 người bạn hoặc 1 nhóm chat");
            title.setText("Trang chủ");
            menuButton.setText("Xin chào, " + baseController.getUser().getName());
            List <User> friendList = ((UserController)baseController).getFriendList(baseController.getUser().getId());
            baseController = new GroupController();
            List <Group> groupList =  ((GroupController) baseController).getGroupList(baseController.getUser().getId());
            displayFriendListAndGroupList(friendList, groupList);

            menuItem1.setOnAction(e -> {
                try {
                    ProfileScreenHandler profileController = new ProfileScreenHandler(this.stage, Config.PROFILE_SCREEN_PATH);
                    profileController.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            menuItem2.setOnAction(e -> {
                try {
                    FriendScreenHandler friendController = new FriendScreenHandler(this.stage, Config.FRIEND_SCREEN_PATH);
                    friendController.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            menuItem3.setOnAction(e -> {
                try {
                    LoginScreenHandler loginController = new LoginScreenHandler(this.stage, Config.LOGIN_SCREEN_PATH);
                    loginController.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnSearch.setOnMouseClicked(e -> {
                String find = textFieldSearch.getText();
                List<User> resultSearchList = new ArrayList<>();
                List<Group> resultSearchList1 = new ArrayList<>();
                for (User user : friendList) {
                    if(user.getName().toLowerCase().contains(find.toLowerCase())) {
                        resultSearchList.add(user);
                    }
                }

                for (Group group : groupList) {
                    if(group.getGroupName().toLowerCase().contains(find.toLowerCase())) {
                        resultSearchList1.add(group);
                    }
                }
                displayFriendListAndGroupList(resultSearchList, resultSearchList1);
            });
            btnCreateGroup.setOnMouseClicked(e -> {
                try {
                    CreateGroupScreenHandler createGroupController = new CreateGroupScreenHandler(this.stage, Config.CREATE_GROUP_SCREEN_PATH);
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
