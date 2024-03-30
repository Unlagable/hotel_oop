package com.example.hms;

import javafx.beans.Observable;
import javafx.beans.value.ObservableSetValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CheckInUserController implements Initializable {
    ObservableList<HotelRoomTable> list_Room = FXCollections.observableArrayList();
    @FXML
    private TableView<HotelRoomTable> list_room_tableview;
    @FXML
    private TableColumn<HotelRoomTable,String> roomNo;
    @FXML
    private TableColumn<HotelRoomTable,String> type;
    @FXML
    private TableColumn<HotelRoomTable,String> capacity;
    @FXML
    private TableColumn<HotelRoomTable,String> price;
    @FXML
    private TableColumn<HotelRoomTable,String> status;
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
        initeCols();
        loadData();
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
    private void initeCols(){
        roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    private void loadData(){
        list_Room.clear();
        list_Room.addAll(Main.availableRoomList);
        list_room_tableview.setItems(list_Room);
    }

}