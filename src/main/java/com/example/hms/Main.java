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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {
    public static ArrayList<HotelRoomTable> roomList = new ArrayList<>();
    public static ArrayList<AllRoomDetail> roomDetails = new ArrayList<>();
    public static ArrayList<HotelRoomTable> availableRoomList = new ArrayList<>();
    public static ArrayList<AllRoomDetail> unavailableRoomList = new ArrayList<>();


    File fl = new File("roomListTable.txt");
    File roomDetailFile = new File("roomDetailFile.txt");
    File userFile = new File("userDetail.txt");
    FileOutputStream fos;
    FileOutputStream fox;

    {
        try {
            fos = new FileOutputStream(fl,true);
            fox = new FileOutputStream(roomDetailFile,true);
            readRoomTable(roomList,fl);
            readDataOfAllRoom(roomDetails,roomDetailFile);
            addToAvailableRoom();
            addToUnavailablelist();
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
            FileOutputStream fox = null;
            FileOutputStream foa = null;


            try {
                fos = new FileOutputStream(fl,false);
                fox = new FileOutputStream(roomDetailFile,false);
                foa = new FileOutputStream(userFile,false);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            PrintWriter pw = new PrintWriter(fos);
            PrintWriter px = new PrintWriter(fox);
            PrintWriter pr = new PrintWriter(foa);
            writeFile(roomList,pw);
            writeDataOfAllRoom(roomDetails,px);
            writeUserFile(LoginController.users,pr);
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
    public static void writeUserFile(ArrayList<UserDetail> users, PrintWriter pw){
        for (UserDetail user:users) {
            pw.println(user.getUsername()+";"+user.getPassword()+";"+user.getEmail()+";"+user.getPhoneNum());
        }
        pw.close();
    }
    public static void writeFile(ArrayList<HotelRoomTable> rooms, PrintWriter pw){
        for (HotelRoomTable room:rooms) {
            pw.println(room.getRoomNo()+";"+room.getType()+";"+room.getCapacity()+";"+room.getPrice()+";"+room.getStatus());
        }
        pw.close();
    }
    public void readDataOfAllRoom(ArrayList<AllRoomDetail> roomDetails, File file) throws FileNotFoundException {
        Scanner cs  = new Scanner(file);
        cs.useDelimiter(";|\r\n");

        while (cs.hasNextLine()){
            AllRoomDetail s = new AllRoomDetail();
            s.setCheckInDate(LocalDate.parse(cs.next()));
            s.setCheckOutDate(readDate(cs));
//            s.setCheckOutDate(cs.next().equalsIgnoreCase("null")? null : LocalDate.parse(cs.next()));
            s.setUser(new UserDetail(cs.next(),"", cs.next(), cs.next()));
            s.setRoomTable(new HotelRoomTable(cs.next(),cs.next(),cs.next(),cs.next(),cs.next()));
            cs.nextLine();
            System.out.println(s);
            roomDetails.add(s);
        }
    }
    public LocalDate readDate(Scanner cs){
        String s = cs.next() ;
        if (s.equalsIgnoreCase("null")){
            return null;
        }
        return LocalDate.parse(s);
    }
    public static void writeDataOfAllRoom(ArrayList<AllRoomDetail> roomDetails, PrintWriter pw){
        for (AllRoomDetail room:roomDetails) {
            pw.println(room.getCheckInDate()+";"+room.getCheckOutDate() +";"+ room.getUser().getUsername() +";"+ room.getUser().getEmail() +";"+ room.getUser().getPhoneNum() +";"+ room.getRoomTable().getRoomNo() +";"+ room.getRoomTable().getType() +";"+ room.getRoomTable().getCapacity() +";"+ room.getRoomTable().getPrice() +";"+ room.getRoomTable().getStatus());
        }
        pw.close();
    }

    public void addToAvailableRoom(){
        for (HotelRoomTable room:roomList) {
            if (room.getStatus().equalsIgnoreCase("Available")){
                availableRoomList.add(room);
                System.out.println(room);
            }
        }
    }
    public void addToUnavailablelist(){
        for (AllRoomDetail room: roomDetails){
            if (room.getRoomTable().getStatus().equalsIgnoreCase("Unavailable")){
                unavailableRoomList.add(room);
            }
        }
    }
}
