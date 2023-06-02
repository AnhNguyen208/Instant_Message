package com.example.instant_message.controller;

import com.example.instant_message.model.User;
import com.example.instant_message.service.FriendService;
import com.example.instant_message.ultils.Config;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class FriendInfoController extends FXMLController {
    @FXML
    private ImageView imageView;
    @FXML
    private Label labelName;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnDenied;
    @FXML
    private Button btnRequestFriend;
    @FXML
    private Button btnUnfriend;
    private User user1;
    private User user2;
    private Stage stage;
    private FriendService friendService;

    public FriendInfoController(String screenPath, User user, Stage stage) throws IOException {
        super(screenPath);
        this.user1 = user;
        this.stage = stage;
        friendService = new FriendService();
    }

    public void setFriendInfo(User user) {
        this.user2 = user;
        labelName.setText(user.getName());
        btnAccept.setVisible(false);
        btnDenied.setVisible(false);
        btnRequestFriend.setVisible(false);

        btnUnfriend.setOnMouseClicked(e -> {
            try {
                friendService.unfriend(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void setFriendSuggestionInfo(User user) {
        this.user2 = user;
        labelName.setText(user.getName());
        btnAccept.setVisible(false);
        btnDenied.setVisible(false);
        btnUnfriend.setVisible(false);

        btnRequestFriend.setOnMouseClicked(e -> {
            try {
                friendService.sendFriendRequest(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void setFriendRequestInfo(User user) {
        this.user2 = user;
        labelName.setText(user.getName());
        btnUnfriend.setVisible(false);
        btnRequestFriend.setVisible(false);

        btnAccept.setOnMouseClicked(e-> {
            try {
                friendService.acceptFriendRequest(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnDenied.setOnMouseClicked(e -> {
            try {
                friendService.deniedFriendRequest(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void reloadPage() throws IOException {
        FriendController friendController = new FriendController(stage, Config.FRIEND_SCREEN_PATH);
        friendController.show();
    }
}
