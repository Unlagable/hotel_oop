package com.example.hms;

import javafx.beans.Observable;
import javafx.beans.value.ObservableSetValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckInController implements Initializable {
    @FXML
    private Label lb_username;
    @FXML
    private Label lb_email;
    @FXML
    private Label lb_phoneNum;
    @FXML
    private Label lb_roomType;
    @FXML
    private Label lb_capacity;
    @FXML
    private Label lb_price;
    @FXML
    private ChoiceBox<String> roomNum;
    @FXML
    private DatePicker checkInDate;

    public void checkInBtn(ActionEvent event){
        for (HotelRoomTable room:Main.availableRoomList) {
            if (room.getRoomNo().equalsIgnoreCase(roomNum.getValue())){
                room.setStatus("Unavailable");
                System.out.println(room);
                Main.availableRoomList.remove(room);
                break;
            }
        }
        lb_roomType.setText("");
        lb_capacity.setText("");
        lb_price.setText("");
        roomNum.setValue("");


    }
    public void getCurrentUser(){
        lb_username.setText(LoginController.currentUser.getUsername());
        lb_email.setText(LoginController.currentUser.getEmail());
        lb_phoneNum.setText(LoginController.currentUser.getPhoneNum());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (HotelRoomTable roomN:Main.availableRoomList) {
            roomNum.getItems().add(roomN.getRoomNo());
        }
        getCurrentUser();
//        Main.availableRoomList.removeIf(roomN -> roomN.getStatus().equalsIgnoreCase("Unavailable"));
        roomNum.setOnAction(this::showOnSelect);
    }
    public void showOnSelect(ActionEvent event){
        String roomNumb= roomNum.getValue();
        for (HotelRoomTable room:Main.availableRoomList) {
            if (room.getRoomNo().equalsIgnoreCase(roomNumb)){
                lb_roomType.setText(room.getType());
                lb_capacity.setText(room.getCapacity());
                lb_price.setText(room.getPrice());
            }
        }
    }

}