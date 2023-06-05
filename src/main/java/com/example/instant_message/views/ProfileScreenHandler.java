package com.example.instant_message.views;

import com.example.instant_message.controller.BaseController;
import com.example.instant_message.controller.UserController;
import com.example.instant_message.ultils.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ProfileScreenHandler extends BaseScreenHandler implements Initializable {
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
    private ImageView avatar;
    @FXML
    private TextField name;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField textFieldDob;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private Button btnEdit;

    public ProfileScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    private void setEditableAndVisible(boolean status) {
        name.setEditable(status);
        email.setEditable(status);
        phoneNumber.setEditable(status);
        dob.setVisible(status);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        baseController = new UserController();
        title.setText("Thông tin cá nhân");
        menuButton.setText("Xin chào, " + baseController.getUser().getName());
        menuItem1.setText("Trang chủ");
        menuItem1.setOnAction(e -> {
            if(btnEdit.getText().equals("Xác nhận")) {
                displayAlert(Alert.AlertType.WARNING, "Cảnh báo", "Hãy xác nhận thay đổi thông tin cá nhân!!!");
            } else {
                try {
                    HomeScreenHandler homeController = new HomeScreenHandler(this.stage, Config.HOME_SCREEN_PATH);
                    homeController.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        menuItem2.setText("Bạn bè");
        menuItem2.setOnAction(e -> {
            if(btnEdit.getText().equals("Xác nhận")) {
                displayAlert(Alert.AlertType.WARNING, "Cảnh báo", "Hãy xác nhận thay đổi thông tin cá nhân!!!");
            } else {
                try {
                    FriendScreenHandler friendController = new FriendScreenHandler(this.stage, Config.FRIEND_SCREEN_PATH);
                    friendController.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        menuItem3.setOnAction(e -> {
            if(btnEdit.getText().equals("Xác nhận")) {
                displayAlert(Alert.AlertType.WARNING, "Cảnh báo", "Hãy xác nhận thay đổi thông tin cá nhân!!!");
            } else {
                try {
                    LoginScreenHandler loginController = new LoginScreenHandler(this.stage, Config.LOGIN_SCREEN_PATH);
                    loginController.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        File file = new File("images/user.png");
        Image image = new Image(file.toURI().toString());
        avatar.setImage(image);

        name.setText(baseController.getUser().getName());
        email.setText(baseController.getUser().getEmail());
        phoneNumber.setText(baseController.getUser().getPhoneNumber());
        dob.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(baseController.getUser().getDob())));
        textFieldDob.setText(new SimpleDateFormat("MM/dd/yyyy").format(baseController.getUser().getDob()).toString());

        setEditableAndVisible(false);
        textFieldDob.setEditable(false);

        btnEdit.setOnMouseClicked(e-> {
            if(btnEdit.getText().equals("Chỉnh sửa thông tin")) {
                setEditableAndVisible(true);
                textFieldDob.setVisible(false);
                btnEdit.setText("Xác nhận");
            } else {
                try {
                    ((UserController) baseController).updateUser(baseController.getUser().getId(), email.getText(),
                            name.getText(), phoneNumber.getText(), dob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    baseController.setUser(baseController.getUser().getId(), email.getText(), name.getText(), phoneNumber.getText(),
                            dob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    setEditableAndVisible(false);

                    textFieldDob.setText(new SimpleDateFormat("MM/dd/yyyy").format(baseController.getUser().getDob()).toString());
                    textFieldDob.setVisible(true);
                    btnEdit.setText("Chỉnh sửa thông tin");
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
