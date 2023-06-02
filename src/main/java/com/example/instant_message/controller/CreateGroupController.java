package com.example.instant_message.controller;

import com.example.instant_message.model.User;
import com.example.instant_message.service.GroupService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateGroupController extends BaseController implements Initializable {
    @FXML
    private TextField groupName;
    @FXML
    private MenuButton groupMember;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button btnCreate;
    private GroupService groupService;
    public CreateGroupController(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Long> idMemberList = new ArrayList<>();
        groupService = new GroupService();
        idMemberList.add(user.getId());
        try {
            List<CheckMenuItem> itemList = new ArrayList<>();
            List<User> friendList = userService.friendList(user.getId());
            for(User user: friendList) {
                CheckMenuItem checkMenuItem = new CheckMenuItem(user.getName());
                checkMenuItem.setId(user.getId().toString());
                itemList.add(checkMenuItem);
            }
            groupMember.setText("Chọn thành viên nhóm");
            groupMember.getItems().addAll(itemList);

            for (CheckMenuItem item : itemList) {
                item.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (newValue) {
                        listView.getItems().add(item.getText());
                        idMemberList.add(Long.valueOf(item.getId()));
                    } else {
                        listView.getItems().remove(item.getText());
                        idMemberList.remove(Long.valueOf(item.getId()));
                    }
                });
            }

            btnCreate.setOnMouseClicked(e -> {
                if(idMemberList.size() < 3) {
                    displayAlert(Alert.AlertType.WARNING, "Cảnh báo", "Thành viên nhóm cần ít nhất 3 người");
                } else if (groupName.getText().isEmpty()) {
                    displayAlert(Alert.AlertType.WARNING, "Cảnh báo", "Hãy điền tên nhóm");
                } else {
                    try {
                        groupService.createGroup(groupName.getText(), idMemberList);
                        this.stage.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
