package com.example.kursovaya;

import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Window registrationWindow = new Window();
        registrationWindow.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

