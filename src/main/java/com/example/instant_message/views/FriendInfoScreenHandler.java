package com.example.instant_message.views;

import com.example.instant_message.controller.BaseController;
import com.example.instant_message.entity.User;
import com.example.instant_message.controller.FriendController;
import com.example.instant_message.ultils.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FriendInfoScreenHandler extends FXMLScreenHandler implements Initializable {
    @FXML
    private ImageView avatar;
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
    private Stage stage;
    private BaseController baseController;

    public FriendInfoScreenHandler(String screenPath, Stage stage) throws IOException {
        super(screenPath);
        this.stage = stage;
        baseController = new FriendController();
    }

    public void setFriendInfo(User user1, User user2) {
        labelName.setText(user2.getName());
        btnAccept.setVisible(false);
        btnDenied.setVisible(false);
        btnRequestFriend.setVisible(false);

        btnUnfriend.setOnMouseClicked(e -> {
            try {
                ((FriendController) baseController).unfriend(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void setFriendSuggestionInfo(User user1, User user2) {
        labelName.setText(user2.getName());
        btnAccept.setVisible(false);
        btnDenied.setVisible(false);
        btnUnfriend.setVisible(false);

        btnRequestFriend.setOnMouseClicked(e -> {
            try {
                ((FriendController) baseController).sendFriendRequest(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void setFriendRequestInfo(User user1, User user2) {
        labelName.setText(user2.getName());
        btnUnfriend.setVisible(false);
        btnRequestFriend.setVisible(false);

        btnAccept.setOnMouseClicked(e-> {
            try {
                ((FriendController) baseController).acceptFriendRequest(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnDenied.setOnMouseClicked(e -> {
            try {
                ((FriendController) baseController).deniedFriendRequest(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void reloadPage() throws IOException {
        FriendScreenHandler friendController = new FriendScreenHandler(stage, Config.FRIEND_SCREEN_PATH);
        friendController.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("images/user.png");
        Image image = new Image(file.toURI().toString());
        avatar.setImage(image);
    }
}
