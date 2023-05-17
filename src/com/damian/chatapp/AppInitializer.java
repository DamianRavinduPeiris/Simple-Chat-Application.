package com.damian.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/com/damian/chatapp/view/client.fxml"))));
            primaryStage.setTitle("Client.");
            primaryStage.show();

            Stage secondaryStage = new Stage();
            secondaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/com/damian/chatapp/view/server.fxml"))));
            secondaryStage.setTitle("Server.");
            secondaryStage.show();
        } catch (IOException e) {
           new Alert(Alert.AlertType.ERROR,"Error while loading the client UI : "+e.getLocalizedMessage()).show();
        }

    }
}
