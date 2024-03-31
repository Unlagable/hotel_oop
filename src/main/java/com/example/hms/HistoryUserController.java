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

public class HistoryUserController implements Initializable {
    ObservableList<AllRoomDetail> userHistoryList = FXCollections.observableArrayList();
    @FXML
    private TableView<AllRoomDetail> userHistoryListTable;


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
    private TableColumn<AllRoomDetail,String> roomPrice;
    private void initeCols(){
        checkInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        checkOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        roomNumber.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getRoomNo()));
        roomType.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getType()));
        roomCapacity.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getCapacity()));
        roomPrice.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomTable().getPrice()));

    }
    private void loadData(){
        userHistoryList.clear();
        userHistoryList.addAll(LoginController.currentUserHistory);
        userHistoryListTable.setItems(userHistoryList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        getCurrentUserHistory();
        initeCols();
        loadData();
    }
    public void refreshBtn(){getCurrentUserHistory();loadData();}
    public void getCurrentUserHistory(){
        LoginController.currentUserHistory.clear();
        for (AllRoomDetail roomHistory: Main.roomDetails) {
            if (roomHistory.getUser().getUsername().equals(LoginController.currentUser.getUsername())){
                LoginController.currentUserHistory.add(roomHistory);
                System.out.println(roomHistory);
                System.out.println("Error");
            }
        }
    }

}
