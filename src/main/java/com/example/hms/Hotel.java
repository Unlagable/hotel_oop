package com.example.hms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {
    static Scanner inp = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<HotelRoomTable> hotel = new ArrayList<>();
        File fl = new File("hotelRoom.txt");
//        FileOutputStream fos = new FileOutputStream(fl,false);
//        PrintWriter pw = new PrintWriter(fos);
//        writeFile(hotel,pw);

        Scanner cs  = new Scanner(fl);
        cs.useDelimiter(";|\r\n");



        while (cs.hasNextLine()){
            HotelRoomTable s = new HotelRoomTable();
            s.setRoomNo(cs.next());
            s.setType(cs.next());
            s.setCapacity(cs.next());
            s.setPrice(cs.next());
            s.setStatus(cs.next());
            cs.nextLine();
            hotel.add(s);
        }


        while (true){
            menu();
            int op = inp.nextInt();
            switch (op){
                case 1:
                    addRoom(hotel);
                    break;
                case 2:
                    printAll(hotel);
                    break;
                case 3:
                    System.out.print("Enter Room Number: ");
                    String num = inp.next();
                    searchRoomNumber(hotel,num);
                    break;
                case 4:
                    System.out.print("status: ");
                    String status = inp.next();
                    sortByStatus(hotel,status);
                    break;
                case 5:
//                    sortByName(hotel);
                    break;
                case 6:
//                    sortById(hotel);
                    break;
                case 7:
                    System.out.print("Enter Student's ID: ");
                    String rnum = inp.next();
                    deleteRoom(hotel,rnum);
                    break;
                case 0:
                    FileOutputStream fos = new FileOutputStream(fl,false);
                    PrintWriter pw = new PrintWriter(fos);
                    writeFile(hotel,pw);
                    System.exit(0);
                default:
                    System.out.println("Invalid input.Try again");
            }
        }
    }
    public static void menu(){
        System.out.println("Hotel Menu");
        System.out.println("1. Add Room");
        System.out.println("2. List All Room");
        System.out.println("3. Search Room by Number");
        System.out.println("4. Sort Room By Status");
        System.out.println("7. Remove Room By Number");
        System.out.println("0. Save and Exit");
        System.out.print("Please select an option: ");
    }
    public static void addRoom(ArrayList<HotelRoomTable> hotels){
        System.out.println("Add a Room:");
        System.out.print("Room Nº: ");
        String roomno = inp.next();
        System.out.print("Room Type: ");
        String type = inp.next();
        System.out.print("Room Capacity: ");
        String cap = inp.next();
        System.out.print("Price: ");
        String price = inp.next();
        System.out.print("Status: ");
        String status = inp.next();

        hotels.add(new HotelRoomTable(roomno,type,cap,price,status));
    }

    public static void printAll(ArrayList<HotelRoomTable> hotels){
        for (HotelRoomTable hotel:hotels) {
            System.out.println(hotel);
        }
    }
    public static void searchRoomNumber(ArrayList<HotelRoomTable> hotels,String roomnum){
        for (HotelRoomTable room:hotels) {
            if (room.getRoomNo().equalsIgnoreCase(roomnum)){
                System.out.println(room);
                return;
            }
        }
        System.out.println("Student not found!");
    }
    public static void sortByStatus(ArrayList<HotelRoomTable> hotels,String status){
        for (HotelRoomTable room:hotels) {
            if (room.getStatus().equalsIgnoreCase(status)){
                System.out.println(room);
                return;
            }
        }
        System.out.println("Room not found!");
    }

    public static void deleteRoom(ArrayList<HotelRoomTable> hotel,String num){

        for (HotelRoomTable room:hotel) {
            if (room.getRoomNo().equalsIgnoreCase(num)){
                hotel.remove(room);
                System.out.println("Room is deleted!");
                return;
            }
        }
        System.out.println("Room not found!");
    }
    public static void writeFile(ArrayList<HotelRoomTable> hotel,PrintWriter pw){
        for (HotelRoomTable room:hotel) {
            pw.println(room.getRoomNo()+";"+room.getType()+";"+room.getCapacity()+";"+room.getPrice()+";"+room.getStatus());
        }
        pw.close();
    }
}
