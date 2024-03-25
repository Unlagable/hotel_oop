package com.example.hms;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Room {
    private final SimpleIntegerProperty number = new SimpleIntegerProperty(0);
    private final SimpleStringProperty type = new SimpleStringProperty("");
    private final SimpleStringProperty capacity = new SimpleStringProperty("");
    private final SimpleIntegerProperty price = new SimpleIntegerProperty(0);
    private final SimpleStringProperty status = new SimpleStringProperty("");
    public Room() {
        this(0, " ", " ",0, " ");
    }

    public Room(int number, String type, String capacity, int price, String status) {
        setNumber(number);
        setType(type);
        setCapacity(capacity);
        setPrice(price);
        setStatus(status);
    }

    public void display(){
        System.out.println(number+" "+ type+" "+capacity+" "+price + " "+ status+ " ");
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getCapacity() {
        return capacity.get();
    }

    public SimpleStringProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity.set(capacity);
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

}
