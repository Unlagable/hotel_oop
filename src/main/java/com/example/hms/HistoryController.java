package com.example.hms;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    ObservableList<AllRoomDetail> historyList = FXCollections.observableArrayList();
    @FXML
    private TableView<AllRoomDetail> historyListTable;


    @FXML
    private TableColumn<AllRoomDetail,String> username;
    @FXML
    private TableColumn<AllRoomDetail,String> email;
    @FXML
    private TableColumn<AllRoomDetail,String> phoneNumber;
    @FXML
    private TableColumn<AllRoomDetail, LocalDate> checkInDate;
    @FXML
    private TableColumn<AllRoomDetail,LocalDate> checkOutDate;
    @FXML
    private TableColumn<AllRoomDetail,String> roomNumber;
    @FXML
    private TableColumn<AllRoomDetail,String> roomType;
    @FXML
    private TableColumn<AllRoomDetail,String> roomCapacity;
    @FXML
    private TableColumn<AllRoomDetail,String> price;

    private void initeCols(){
        username.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getUsername()));
        roomNumber.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getRoomNo()));
        checkInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        checkOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getEmail()));
        phoneNumber.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getPhoneNum()));
        roomType.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getType()));
        roomCapacity.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getCapacity()));
//        roomStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getStatus()));
        price.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getPrice()));


    }
    private void loaddata(){
        historyList.clear();
        historyList.addAll(Main.roomDetails);
        historyListTable.setItems(historyList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initeCols();
        loaddata();
    }
    public void refreshBtn(){loaddata();}
}
