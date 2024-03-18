package com.example.hms;

public class HotelRoomTable {
    String roomNo;
    String type;
    String capacity;
    String price;
    String status;
    public HotelRoomTable(String roomNo, String type, String capacity, String price, String status){
        this.roomNo = roomNo;
        this.type = type;
        this.capacity = capacity;
        this.price = price;
        this.status = status;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getType() {
        return type;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
