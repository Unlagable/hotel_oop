package com.example.hms;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class AddRoomController {
    private static final String DATA_FILE_PATH = "rooms.txt";

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
    protected void onSubmit(Event e) {
        if (txt_type.getText().length() > 0 && txt_number.getText().length() > 0) {
            Room room = new Room();
            room.setNumber(Integer.parseInt(txt_number.getText()));
            room.setType(txt_type.getText());
            room.setCapacity(txt_capacity.getText());
            room.setPrice(Integer.parseInt(txt_price.getText()));
            room.setStatus(txt_status.getText());
            txt_number.setText("");
            txt_type.setText("");
            txt_capacity.setText("");
            txt_price.setText("");
            txt_status.setText("");

            saveRoom(room);
            System.out.println("Save Success!");
            lb_mess.setText("Save Success!");
            lb_mess_error.setText("");
        } else {
            lb_mess_error.setText("You should input number and type!");
            lb_mess.setText("");
            System.out.println("You should input number and type!");
        }
    }

    public void addRoom(ActionEvent event) throws IOException {
        File file = new File("hotelRoom.txt");
        String fxmlName = "list-room-view.fxml";

        int number = Integer.parseInt(txt_number.getText());
        String type = txt_type.getText();
        String capacity = txt_capacity.getText();
        int price = Integer.parseInt(txt_price.getText());
        String status = txt_status.getText();

        Room room = new Room(number, type, capacity, price, status);
        writeRoomToFile(room, file);

        readFile(file);
    }

    @FXML
    protected void onShowList(Event event) throws IOException {
        Parent root = FXMLLoader.load(Apps.class.getResource("home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setWidth(stage.getWidth());
        stage.setHeight(stage.getHeight());
        stage.setTitle("List Room");
        stage.setScene(scene);
        stage.show();
    }

    private void saveRoom(Room room) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE_PATH, true))) {
            writer.println(room.getNumber() + "," + room.getType() + "," + room.getCapacity() + "," + room.getPrice() + "," + room.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeRoomToFile(Room room, File file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println(room.getNumber() + "," + room.getType() + "," + room.getCapacity() + "," + room.getPrice() + "," + room.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] roomData = line.split(",");
                int number = Integer.parseInt(roomData[0]);
                String type = roomData[1];
                String capacity = roomData[2];
                int price = Integer.parseInt(roomData[3]);
                String status = roomData[4];

                Room room = new Room(number, type, capacity, price, status);
                System.out.println(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}