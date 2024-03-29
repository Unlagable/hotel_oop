package com.example.hms;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CheckOutController implements Initializable {
    @FXML
    private Label lb_username;
    @FXML
    private Label lb_checkindate;
    @FXML
    private Label lb_email;
    @FXML
    private Label lb_phoneNumber;
    @FXML
    private Label lb_roomType;
    @FXML
    private Label lb_capacity;
    @FXML
    private Label lb_price;
    @FXML
    private ChoiceBox<String> selectRoomNumber;
    @FXML
    private DatePicker checkOutDate;

    ObservableList<AllRoomDetail> checkOutList = FXCollections.observableArrayList();
    @FXML
    private TableView<AllRoomDetail> checkOutListTable;
    @FXML
    private TableColumn<AllRoomDetail,String> username;
    @FXML
    private TableColumn<AllRoomDetail,String> email;
    @FXML
    private TableColumn<AllRoomDetail,String> phoneNumber;
    @FXML
    private TableColumn<AllRoomDetail, LocalDate> checkInDate;
    @FXML
    private TableColumn<AllRoomDetail,LocalDate> checkOutDateCol;
    @FXML
    private TableColumn<AllRoomDetail,String> roomNumber;
    @FXML
    private TableColumn<AllRoomDetail,String> roomType;
    @FXML
    private TableColumn<AllRoomDetail,String> roomCapacity;
    @FXML
    private TableColumn<AllRoomDetail,String> roomPrice;
    @FXML
    private TableColumn<AllRoomDetail,String> roomStatus;
    @FXML
    private TableColumn<AllRoomDetail,String> price;


    private void initeCols(){
        username.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getUsername()));
        roomNumber.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getRoomNo()));
        checkInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getEmail()));
//        phoneNumber.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getPhoneNum()));
//        roomType.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getType()));
//        roomCapacity.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getCapacity()));
//        price.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getPrice()));
        roomStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getStatus()));
        price.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getPrice()));

    }
    private void loaddata(){
        checkOutList.clear();
        checkOutList.addAll(Main.unavailableRoomList);
        checkOutListTable.setItems(checkOutList);
    }
    public void checkOutBtn(ActionEvent event){
        if(!(selectRoomNumber.getValue()==null)) {
            for (AllRoomDetail room : Main.unavailableRoomList) {
                if (room.getRoomTable().getRoomNo().equalsIgnoreCase(selectRoomNumber.getValue())) {
                    room.getRoomTable().setStatus("Available");
                    AllRoomDetail roomDetail = new AllRoomDetail(room.getUser(), room.getRoomTable(), room.getCheckInDate(), checkOutDate.getValue());
                    findUnavailableRoom(selectRoomNumber.getValue());
                    updateCheckout(room, roomDetail);
                    Main.unavailableRoomList.remove(room);


                    break;
                }
            }
            lb_username.setText("");
            lb_checkindate.setText("");
            lb_email.setText("");
            lb_phoneNumber.setText("");
            lb_roomType.setText("");
            lb_capacity.setText("");
            lb_price.setText("");
            selectRoomNumber.setValue("");
            checkOutDate.setValue(null);
            refreshBtn();
            System.out.println("succeed");
        }else System.out.println("This is empty");

    }
    public void updateCheckout(AllRoomDetail room, AllRoomDetail updated){
        for (AllRoomDetail list: Main.roomDetails) {
            if (list.getUser().equals(room.getUser()) && list.getRoomTable().equals(room.getRoomTable()) && list.getCheckInDate().equals(room.getCheckInDate()) && list.getCheckOutDate()==null ){
                Main.roomDetails.set(Main.roomDetails.indexOf(room),updated);
            }
        }
    }
    public void findUnavailableRoom(String x){
        for (HotelRoomTable room:Main.roomList) {
            if (room.getRoomNo().equalsIgnoreCase(x)){
                room.setStatus("Available");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initeCols();
        getUnavailableRoom();
        loaddata();
//        Main.availableRoomList.removeIf(roomN -> roomN.getStatus().equalsIgnoreCase("Unavailable"));
        selectRoomNumber.setOnAction(this::showOnSelect);
    }
    public void showOnSelect(ActionEvent event){
        String roomNumb = selectRoomNumber.getValue();
        for (AllRoomDetail room:Main.unavailableRoomList) {
            if (room.getRoomTable().getRoomNo().equalsIgnoreCase(roomNumb)){
                lb_username.setText(room.getUser().getUsername());
                lb_checkindate.setText(room.getCheckInDate().toString());
                lb_email.setText(room.getUser().getEmail());
                lb_phoneNumber.setText(room.getUser().getEmail());
                lb_roomType.setText(room.getRoomTable().getType());
                lb_capacity.setText(room.getRoomTable().getCapacity());
                lb_price.setText(room.getRoomTable().getPrice());

            }
        }
    }
    public void getUnavailableRoom(){
        selectRoomNumber.getItems().clear();
        for (AllRoomDetail room: Main.unavailableRoomList){
            selectRoomNumber.getItems().add(room.getRoomTable().getRoomNo());
//            System.out.println(room);
        }
    }
    public void refreshBtn(){getUnavailableRoom();loaddata();}

}
