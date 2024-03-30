package com.example.hms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignUpController {
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_phoneNum;
    @FXML
    private PasswordField txt_password;
    @FXML
    private PasswordField txt_confirmpass;
    private boolean correct = true;
    public Stage stage;
    public Scene scene;

    public void signUpBtn(ActionEvent event){
        checkInputInfo();
        if (correct) {
            adduser();
            clearField();
        }
    }

    public void adduser(){
        UserDetail signUpUser = new UserDetail(txt_username.getText(),txt_password.getText(),txt_email.getText(),txt_phoneNum.getText());
        LoginController.users.add(signUpUser);
        System.out.println(signUpUser);
    }
    public void confirmPassword(){
        if (txt_password.getText().equals(txt_confirmpass.getText())){
            checkUserWithDB();
        }else {
            correct=false;
            System.out.println("passdiff");
            Alert passdiff = new Alert(Alert.AlertType.ERROR);
            passdiff.setTitle("Incorrect Info");
            passdiff.setHeaderText("Password Incorrect!");
            passdiff.setContentText("Your Password And Confirmation Password Are Different!");
            passdiff.showAndWait();
        }
    }
    public void checkInputInfo(){
        if(!(txt_username.getText().isEmpty()) && !(txt_email.getText().isEmpty()) && !(txt_phoneNum.getText().isEmpty()) && !(txt_password.getText().isEmpty()) && !(txt_confirmpass.getText().isEmpty())){
            confirmPassword();
        }else {
            correct = false;
            System.out.println("mt");
            Alert info = new Alert(Alert.AlertType.ERROR);
            info.setTitle("Incorrect Info");
            info.setHeaderText("Empty Field!");
            info.setContentText("Please Enter All Information!");
            info.showAndWait();
        }
    }

    public void checkUserWithDB(){
        for (UserDetail user: LoginController.users) {
            if (txt_username.getText().equalsIgnoreCase(user.getUsername())){
                correct = false;
                System.out.println("user exist");
                Alert info = new Alert(Alert.AlertType.ERROR);
                info.setTitle("Incorrect Info");
                info.setHeaderText("Username Already Exist!");
                info.setContentText("Please Try Other Username!");
                info.showAndWait();

                break;
            }
        }

    }
    public void clearField(){
        txt_username.setText("");
        txt_email.setText("");
        txt_phoneNum.setText("");
        txt_password.setText("");
        txt_confirmpass.setText("");
    }
    public void cancelBtn(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Signing Up");
        alert.setHeaderText("Do You Want To Cancel Signing Up?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("firstlogin-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Hotel Login");
            stage.setScene(scene);
            stage.show();
        }
    }

}
