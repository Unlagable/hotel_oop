package com.example.hms;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddRoomController {
    @FXML
    protected TextField txt_number;
    @FXML
    protected TextField txt_type;
    @FXML
    protected TextField txt_capacity;
    @FXML
    protected TextField txt_price;
    @FXML
    protected TextField txt_status;

    @FXML
    protected Label lb_mess;
    @FXML
    protected Label lb_mess_error;
    @FXML
    protected void onSubmit(Event e){
        if(txt_type.getText().length()>0 &&
                txt_number.getText().length()>0) {
//            User u = new User(txt_name.getText(),txt_gen.getText(),
//                    Integer.parseInt(txt_age.getText()));
            Room u = new Room();
//            u.name = txt_name.getText();
            u.setNumber(Integer.parseInt(txt_number.getText()));
            u.setType(txt_type.getText());
            u.setCapacity(txt_capacity.getText());
            u.setPrice(Integer.parseInt(txt_price.getText()));
            u.setStatus(txt_status.getText());
            txt_number.setText("");
            txt_type.setText("");
            txt_capacity.setText("");
            txt_price.setText("");
            txt_status.setText("");

            Apps.ulist.add(u);
            System.out.println("Save Success !");
            lb_mess.setText("Save Success !");
            lb_mess_error.setText("");
//        u.display();
//            System.out.println("submit" + txt_name.getText() + " " + txt_age.getText());
        }else{
            lb_mess_error.setText("you should input name and age!");
            lb_mess.setText("");
            System.out.println("you should input name and age!");
        }
    }

    @FXML
    protected  void onShowList(Event event) throws IOException {
       /*
        System.out.println("List User :");
        for (int i = 0; i <ulist.size() ; i++) {
           User u = ulist.get(i);
           u.display();
        }

        //  */
        Parent root = FXMLLoader.load(Apps.class.getResource("home-view.fxml"));
        Stage stage = (Stage) ( (Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setWidth(stage.getWidth());
        stage.setHeight(stage.getHeight());
        stage.setTitle("List Room");
        stage.setScene(scene);
        stage.show();
    }
}
