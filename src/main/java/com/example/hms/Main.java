package com.example.hms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {
    public static ArrayList<HotelRoomTable> roomList = new ArrayList<>();
    File fl = new File("roomListTable.txt");
    FileOutputStream fos;

    {
        try {
            fos = new FileOutputStream(fl,true);
            readRoomTable(roomList,fl);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

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
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(fl,false);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            PrintWriter pw = new PrintWriter(fos);
            writeFile(roomList,pw);
            stage.close();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void readRoomTable(ArrayList<HotelRoomTable> rooms, File fl) throws FileNotFoundException {

//        FileOutputStream fos = new FileOutputStream(fl,true);
//        PrintWriter pw = new PrintWriter(fos);
//        writeFile(hotel,pw);

        Scanner cs  = new Scanner(fl);
        cs.useDelimiter(";|\r\n");

        while (cs.hasNextLine()){
            HotelRoomTable s = new HotelRoomTable();
            s.setRoomNo(cs.next());
            s.setType(cs.next());
            s.setCapacity(cs.next());
            s.setPrice(cs.next());
            s.setStatus(cs.next());
            cs.nextLine();
            System.out.println(s);
            rooms.add(s);
        }

    }
    public static void writeFile(ArrayList<HotelRoomTable> rooms, PrintWriter pw){
        for (HotelRoomTable room:rooms) {
            pw.println(room.getRoomNo()+";"+room.getType()+";"+room.getCapacity()+";"+room.getPrice()+";"+room.getStatus());
        }
        pw.close();
    }
}
