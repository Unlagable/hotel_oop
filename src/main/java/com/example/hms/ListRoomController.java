package com.example.hms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListRoomController implements Initializable {
    ObservableList<HotelRoomTable>  list = FXCollections.observableArrayList();
    @FXML
    private TableView<HotelRoomTable> list_user_tableview;
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

    private void initeCols(){
        roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initeCols();
        loadData();

    }

    private void loadData(){
//        list.removeAll(list); // should remove
//        /*
//        list.addAll(
//                new User("sok","male",20),
//                new User("sao","male",25),
//                new User("jonh","male",30),
//                new User("dara","male",22)
//        );
//        //*/
//       list.addAll(Apps.ulist);
        list.addAll(Main.roomList);
        list_user_tableview.setItems(list);
    }

    @FXML
    protected  void onAddUser(Event event) throws IOException {
//        Parent root1 = FXMLLoader.load(Apps.class.getResource("addroom-view.fxml"));
//        Stage stage = (Stage) ( (Node)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root1);
//        stage.setTitle("Add Room");
//        stage.setWidth(stage.getWidth());
//        stage.setHeight(stage.getHeight());
//        stage.setScene(scene);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addroom-view.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Room");
        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }

    @FXML
    protected  void refreshBtn(ActionEvent event){
        list.clear();
        loadData();

//        list.clear();
//        list.addAll(Apps.ulist);
//        list_user_tableview.getItems().add(Main.roomList.get(0));

    }


}
