package com.example.kursovaya;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class start extends Application {
    public static Stage stage;

    @Override
    public void start(Stage st) throws Exception{
        stage = st;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signIn.fxml")));
        stage.setTitle("Auto Repair Shop");
        stage.setScene(new Scene(root, 700, 400));
        stage.show();
    }
    public static void swapScene(String fileName){
        try {
            Parent screen = FXMLLoader.load(Objects.requireNonNull(start.class.getResource(fileName)));
            stage.setScene(new Scene(screen));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

