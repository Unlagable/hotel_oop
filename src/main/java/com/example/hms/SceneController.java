package com.example.hms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Scene scene;

    private Parent root;




    public void userLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userlogin-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("User Login");
        stage.setScene(scene);
        stage.show();
    }

    public void adminLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminLogin-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Admin Login");
        stage.setScene(scene);
        stage.show();
    }
    public void backToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("firstlogin-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Hotel Login");
        stage.setScene(scene);
        stage.show();
    }

    public void writeFile(ArrayList<HotelRoomTable> hotel, PrintWriter pw){
        for (HotelRoomTable room:hotel) {
            pw.println(room.getRoomNo()+";"+room.getType()+";"+room.getCapacity()+";"+room.getPrice()+";"+room.getStatus());
        }
        pw.close();
    }
}
