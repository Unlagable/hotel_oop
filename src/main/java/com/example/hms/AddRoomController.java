package com.example.hms;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRoomController implements Initializable {
    @FXML
    protected TextField txt_RoomNo;
    @FXML
    protected TextField txt_type;
    @FXML
    protected TextField txt_capacity;
    @FXML
    protected TextField txt_price;
    @FXML
    protected ChoiceBox<String> choiceBox_Status;
    @FXML
    protected Label lb_mess;
//    @FXML
//    protected Label lb_mess_error;
    private final String[] status = {"Available","Unavailable"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox_Status.getItems().addAll(status);
    }

    public void cancelBtn(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void addRoomBtn(ActionEvent event){
        if (!txt_RoomNo.getText().isEmpty() && !txt_type.getText().isEmpty() && !txt_capacity.getText().isEmpty() && !txt_price.getText().isEmpty() && !choiceBox_Status.getSelectionModel().isEmpty()){
            HotelRoomTable room = new HotelRoomTable(txt_RoomNo.getText(),txt_type.getText(),txt_capacity.getText(),txt_price.getText(),choiceBox_Status.getValue());




            Main.roomList.add(room);
            Main.availableRoomList.add(room);
            System.out.println("Add Succeed");
            lb_mess.setText("Room Successfully Added");
            System.out.println(room);
            txt_RoomNo.setText("");
            txt_type.setText("");
            txt_capacity.setText("");
            txt_price.setText("");
            choiceBox_Status.setValue("");

        }else {
            lb_mess.setText("Please Enter All Information");
        }

    }




//    @FXML
//    protected void onSubmit(Event e){
//        if(txt_type.getText().length()>0 &&
//                txt_RoomNo.getText().length()>0) {
////            User u = new User(txt_name.getText(),txt_gen.getText(),
////                    Integer.parseInt(txt_age.getText()));
//            Room u = new Room();
////            u.name = txt_name.getText();
//            u.setNumber(Integer.parseInt(txt_RoomNo.getText()));
//            u.setType(txt_type.getText());
//            u.setCapacity(txt_capacity.getText());
//            u.setPrice(Integer.parseInt(txt_price.getText()));
//            u.setStatus(txt_status.getText());
//            txt_RoomNo.setText("");
//            txt_type.setText("");
//            txt_capacity.setText("");
//            txt_price.setText("");
//            txt_status.setText("");
//
//            Apps.ulist.add(u);
//            System.out.println("Save Success !");
//            lb_mess.setText("Save Success !");
//            lb_mess_error.setText("");
////        u.display();
////            System.out.println("submit" + txt_name.getText() + " " + txt_age.getText());
//        }else{
//            lb_mess_error.setText("you should input name and age!");
//            lb_mess.setText("");
//            System.out.println("you should input name and age!");
//        }
//    }
//
//    @FXML
//    protected  void onShowList(Event event) throws IOException {
//       /*
//        System.out.println("List User :");
//        for (int i = 0; i <ulist.size() ; i++) {
//           User u = ulist.get(i);
//           u.display();
//        }
//
//        //  */
//        Parent root = FXMLLoader.load(Apps.class.getResource("home-view.fxml"));
//        Stage stage = (Stage) ( (Node)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setWidth(stage.getWidth());
//        stage.setHeight(stage.getHeight());
//        stage.setTitle("List Room");
//        stage.setScene(scene);
//        stage.show();
//    }





}
