package com.example.hms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class LoginController {
    public Stage stage;
    public Scene scene;

    public Parent root;
    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;
    ArrayList<UserDetail> users = new ArrayList<>();
    ArrayList<UserDetail> admin = new ArrayList<>();
//    File fl = new File("userDetail.txt");


    public void getHomeScene(ActionEvent event,String fName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fName));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
//        stage.setTitle("Welcome to Golden Hotel");
        stage.setScene(scene);
        stage.show();
    }

    public void login(ActionEvent event) throws IOException {


//        ArrayList<HotelRoomTable> hotel = new ArrayList<>();
        File fl = new File("userDetail.txt");
        String fName = "home-view.fxml";
//        FileOutputStream fos = new FileOutputStream(fl,true);
//        PrintWriter pw = new PrintWriter(fos);
//        writeFile(hotel,pw);

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        readFile(users,fl);
        checkLogin(event,username,password,fName,users);

    }
    public void loginAdmin(ActionEvent event) throws IOException {


//        ArrayList<HotelRoomTable> hotel = new ArrayList<>();
        File fl = new File("adminDetail.txt");
        String fName = "home-view.fxml";
//        FileOutputStream fos = new FileOutputStream(fl,true);
//        PrintWriter pw = new PrintWriter(fos);
//        writeFile(hotel,pw);

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        readFile(admin,fl);
        checkLogin(event,username,password,fName,admin);

    }
    public void checkLogin(ActionEvent event, String username, String password, String fName, ArrayList<UserDetail> users) throws IOException {


        for (UserDetail user:users) {
            System.out.println("check");
            System.out.println(user);
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)){
                System.out.println("Login Correct");
                getHomeScene(event,fName);
                break;
            }
            System.out.println("Incorrect Login");
        }

    }
    public void readFile(ArrayList<UserDetail> users, File fl) throws FileNotFoundException {

//        FileOutputStream fos = new FileOutputStream(fl,true);
//        PrintWriter pw = new PrintWriter(fos);
//        writeFile(hotel,pw);

        Scanner cs  = new Scanner(fl);
        cs.useDelimiter(";|\r\n");

        while (cs.hasNextLine()){
            UserDetail s = new UserDetail();
            s.setUsername(cs.next());
            s.setPassword(cs.next());
            s.setEmail(cs.next());
            s.setPhoneNum(cs.next());
            cs.nextLine();
            System.out.println(s);
            users.add(s);
        }

    }
    public void backToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("firstlogin-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Hotel Login");
        stage.setScene(scene);
        stage.show();
    }


}
