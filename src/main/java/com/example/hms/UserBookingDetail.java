package com.example.hms;

public class UserBookingDetail extends HotelRoomTable{

    String date;
    boolean pay;
    UserBookingDetail(String date,boolean pay,String roomNo, String type, String capacity, String price, String status){
        super(roomNo,type,capacity,price,status);
        this.date = date;
        this.pay = pay;
    }

    @Override
    public String getCapacity() {
        return super.getCapacity();
    }

    @Override
    public String getPrice() {
        return super.getPrice();
    }

    @Override
    public String getRoomNo() {
        return super.getRoomNo();
    }

    @Override
    public String getStatus() {
        return super.getStatus();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    public String getDate() {
        return date;
    }
    public boolean getPay(){
        return pay;
    }

    @Override
    public void setCapacity(String capacity) {
        super.setCapacity(capacity);
    }

    @Override
    public void setPrice(String price) {
        super.setPrice(price);
    }

    @Override
    public void setRoomNo(String roomNo) {
        super.setRoomNo(roomNo);
    }

    @Override
    public void setStatus(String status) {
        super.setStatus(status);
    }

    @Override
    public void setType(String type) {
        super.setType(type);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }
}
