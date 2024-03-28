package com.example.hms;

import java.time.LocalDate;

public class AllRoomDetail {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private UserDetail user;

    private HotelRoomTable roomTable;



    public AllRoomDetail (){}
    public AllRoomDetail(UserDetail user,HotelRoomTable roomTable,LocalDate checkInDate){
        user.setPassword("Womp Womp");
        this.user = user;
        this.roomTable = roomTable;
        this.checkInDate = checkInDate;
    }
    public AllRoomDetail(UserDetail user,HotelRoomTable roomTable,LocalDate checkInDate,LocalDate checkOutDate){
        user.setPassword("Womp Womp");
        this.user = user;
        this.roomTable = roomTable;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public void setUser(UserDetail user) {
        this.user = user;
        this.user.setPassword("womp womp");
    }

    public void setRoomTable(HotelRoomTable roomTable) {
        this.roomTable = roomTable;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public UserDetail getUser() {
        return user;
    }

    public HotelRoomTable getRoomTable() {
        return roomTable;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "allRoomDetail{" +
                "checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", user=" + user +
                ", roomTable=" + roomTable +
                '}';
    }
}
