package com.example.instant_message.controller;

import javafx.application.Application;
import javafx.stage.Stage;

import java.net.URL;

public class DemoApplication1 extends Application {
    @Override
    public void start(Stage stage) {
        URL fxmlLocation = getClass().getResource("/com/example/instant_message/splash.fxml");
        System.out.println(fxmlLocation);
    }

    public static void main(String[] args) {
        launch();
    }
}