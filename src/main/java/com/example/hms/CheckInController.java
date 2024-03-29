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
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CheckInController implements Initializable {
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
    private TextField txt_username;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_phoneNum;
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
        if (!txt_username.getText().isEmpty() && !txt_phoneNum.getText().isEmpty() && !txt_email.getText().isEmpty() && !(checkInDate.getValue()==null) && !(roomNum.getValue()==null)) {
            for (HotelRoomTable room : Main.availableRoomList) {
                if (room.getRoomNo().equalsIgnoreCase(roomNum.getValue())) {
                    room.setStatus("Unavailable");
                    UserDetail user = new UserDetail(txt_username.getText(), "nopass", txt_email.getText(), txt_phoneNum.getText());
                    AllRoomDetail roomDetail = new AllRoomDetail(user, room, checkInDate.getValue());
                    Main.roomDetails.add(roomDetail);
                    Main.unavailableRoomList.add(roomDetail);
                    Main.availableRoomList.remove(room);


                    break;
                }
            }
            refreshBtn();
            System.out.println("succeed");
        }else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Please Enter All Information");
            errorAlert.showAndWait();
            System.out.println("error");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initeCols();
        loadData();
        getAvailableRoom();
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
    public void clearAll(){
        txt_email.setText("");
        txt_username.setText("");
        txt_phoneNum.setText("");
        lb_roomType.setText("");
        lb_capacity.setText("");
        lb_price.setText("");
        roomNum.setValue("");
        checkInDate.setValue(null);
    }

    public void refreshBtn(){getAvailableRoom();loadData();clearAll();}

}
