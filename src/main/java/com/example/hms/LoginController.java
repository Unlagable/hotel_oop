package com.example.hms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class LoginController {
    public Stage stage;
    public Scene scene;

    public Parent root;
    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    protected Label lb_mess;
    ArrayList<UserDetail> users = new ArrayList<>();
    ArrayList<UserDetail> admin = new ArrayList<>();

    public static UserDetail currentUser = new UserDetail();
    public static ArrayList<AllRoomDetail> currentUserHistory = new ArrayList<>();
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
        String fName = "homeAdmin-view.fxml";
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
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                lb_mess.setText("");

                System.out.println("Login Correct");
                currentUser.setUsername(user.getUsername());
                currentUser.setPassword(user.getPassword());
                currentUser.setEmail(user.getEmail());
                currentUser.setPhoneNum(user.getPhoneNum());
                getCurrentUserHistory();
                getHomeScene(event,fName);
                break;
            }
            System.out.println("Incorrect Login");
            lb_mess.setText("Incorrect Login!");
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
//        scene.getStylesheets().add(getClass().getResource("firstlogin-style.css").toExternalForm());
        stage.setTitle("Hotel Login");
        stage.setScene(scene);
        stage.show();
    }
    public void backToLogin2(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("You're about to logout!");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("firstlogin-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
//            scene.getStylesheets().add(getClass().getResource("firstlogin-style.css").toExternalForm());
            stage.setTitle("Hotel Login");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void checkInfoBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Userdetail-view.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("User Info");
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    public void getCurrentUserHistory(){
        for (AllRoomDetail roomHistory: Main.roomDetails) {
            if (roomHistory.getUser().getUsername().equals(currentUser.getUsername())){
                currentUserHistory.add(roomHistory);
                System.out.println(roomHistory);
            }
        }
    }
}
