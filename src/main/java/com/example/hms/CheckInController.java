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
import java.time.LocalDate;
import java.util.List;
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
                AllRoomDetail roomDetail = new AllRoomDetail(LoginController.currentUser,room,checkInDate.getValue());
                Main.roomDetails.add(roomDetail);
                Main.unavailableRoomList.add(roomDetail);
                Main.availableRoomList.remove(room);


                break;
            }
        }
        lb_roomType.setText("");
        lb_capacity.setText("");
        lb_price.setText("");
        roomNum.setValue("");
        checkInDate.setValue(null);

    }
    public void getCurrentUser(){
        lb_username.setText(LoginController.currentUser.getUsername());
        lb_email.setText(LoginController.currentUser.getEmail());
        lb_phoneNum.setText(LoginController.currentUser.getPhoneNum());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAvailableRoom();
        getCurrentUser();
//        Main.availableRoomList.removeIf(roomN -> roomN.getStatus().equalsIgnoreCase("Unavailable"));
        roomNum.setOnAction(this::showOnSelect);
    }
    public void showOnSelect(ActionEvent event){
        String roomNumb = roomNum.getValue();
        for (HotelRoomTable room:Main.availableRoomList) {
            if (room.getRoomNo().equalsIgnoreCase(roomNumb)){
                lb_roomType.setText(room.getType());
                lb_capacity.setText(room.getCapacity());
                lb_price.setText(room.getPrice());
            }
        }
    }
    public void getAvailableRoom(){
            roomNum.getItems().clear();
        for (HotelRoomTable roomN:Main.availableRoomList) {
            roomNum.getItems().add(roomN.getRoomNo());
        }
    }


}
