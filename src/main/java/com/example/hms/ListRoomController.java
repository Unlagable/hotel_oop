package com.example.hms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListRoomController implements Initializable {
    ObservableList<Room>  list= FXCollections.observableArrayList();
    @FXML
    private TableView<Room> list_user_tableview;
    @FXML
    private TableColumn<Room,String> type;
    @FXML
    private TableColumn<Room,Integer> number;
    @FXML
    private TableColumn<Room,String> capacity;

    @FXML
    private TableColumn<Room,Integer> price;
    @FXML
    private TableColumn<Room,String> status;

    private void initeCols(){
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initeCols();
        loadDara();
    }

    private void loadDara(){
        list.removeAll(list); // should remove
        /*
        list.addAll(
                new User("sok","male",20),
                new User("sao","male",25),
                new User("jonh","male",30),
                new User("dara","male",22)
        );
        //*/
       list.addAll(Apps.ulist);
       list_user_tableview.setItems(list);
    }

    @FXML
    protected  void onAddUser(Event event) throws IOException {
        Parent root = FXMLLoader.load(Apps.class.getResource("add-room-view.fxml"));
        Stage stage = (Stage) ( (Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Room");
        stage.setWidth(stage.getWidth());
        stage.setHeight(stage.getHeight());
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    protected  void onTest(Event event) throws IOException {

        list.clear();
        list.addAll(Apps.ulist);
        list_user_tableview.setItems(list);

    }


}
