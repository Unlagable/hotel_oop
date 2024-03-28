package com.example.hms;

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
//    @FXML
//    private TableColumn<HotelRoomTable,String> roomNo;
//    @FXML
//    private TableColumn<HotelRoomTable,String> type;
//    @FXML
//    private TableColumn<HotelRoomTable,String> capacity;
//    @FXML
//    private TableColumn<HotelRoomTable,String> price;
    @FXML
    private TableColumn<UserDetail,String> email;
    @FXML
    private TableColumn<HotelRoomTable,String> status;
    @FXML
    private TableColumn<UserDetail,String> username;
    @FXML
    private TableColumn<AllRoomDetail, LocalDate> checkInDate;
    @FXML
    private TableColumn<AllRoomDetail,LocalDate> checkOutDate;
    @FXML
    private TableColumn<HotelRoomTable,String> price;

    private void initeCols(){
        username.setCellValueFactory(new PropertyValueFactory<>("user"));
        checkInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        checkOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        price.setCellValueFactory(new PropertyValueFactory<>("roomTable"));


    }
    private void loaddata(){

        historyList.addAll(Main.roomDetails);
        historyListTable.setItems(historyList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initeCols();
        loaddata();
    }
}
