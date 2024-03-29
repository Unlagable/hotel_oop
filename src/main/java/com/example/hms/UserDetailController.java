package com.example.hms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDetailController implements Initializable {
    @FXML
    private Label lb_username;
    @FXML
    private Label lb_password;
    @FXML
    private Label lb_email;
    @FXML
    private Label lb_phoneNum;
    @FXML
    private Label lb_title;



    public void closeBtn(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getUserInfo();
    }
    public void getUserInfo(){
        lb_title.setText(LoginController.currentUser.getUsername().concat("'s Detail"));
        lb_username.setText(LoginController.currentUser.getUsername());
        lb_password.setText(LoginController.currentUser.getPassword());
        lb_email.setText(LoginController.currentUser.getEmail());
        lb_phoneNum.setText(LoginController.currentUser.getPhoneNum());
    }
}

