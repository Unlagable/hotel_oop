package com.example.hms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("firstlogin-view.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Hotel Login");
            stage.show();
            stage.setOnCloseRequest(event -> {
                event.consume();
                logout(stage);
            });

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void logout(Stage stage){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("You're About To Exit");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out");
            stage.close();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }


}
