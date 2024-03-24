package com.example.hms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDetail {
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    UserDetail(){}
    UserDetail(String username, String password, String email, String phoneNum){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
    static Scanner inp = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<UserDetail> user = new ArrayList<>();
        File fl = new File("userDetail.txt");
//        FileOutputStream fos = new FileOutputStream(fl,false);
//        PrintWriter pw = new PrintWriter(fos);
//        writeFile(hotel,pw);

        Scanner cs  = new Scanner(fl);
        cs.useDelimiter(";|\r\n");



//        while (cs.hasNextLine()){
//            UserDetail s = new UserDetail();
//            s.setUsername(cs.next());
//            s.setPassword(cs.next());
//            s.setEmail(cs.next());
//            s.setPhoneNum(cs.next());
//            cs.nextLine();
//            user.add(s);
//        }


        while (true){
//            menu();
            int op = inp.nextInt();
            switch (op){
                case 1:
//                    addRoom(hotel);
                    break;
                case 2:
//                    printAll(hotel);
                    break;
                case 3:
                    System.out.print("Enter Room Number: ");
//                    String num = inp.next();
//                    searchRoomNumber(hotel,num);
                    break;
                case 4:
                    System.out.print("status: ");
//                    String status = inp.next();
//                    sortByStatus(hotel,status);
                    break;
                case 7:
                    System.out.print("Enter Student's ID: ");
//                    String rnum = inp.next();
//                    deleteRoom(hotel,rnum);
                    break;
                case 0:
//                    FileOutputStream fos = new FileOutputStream(fl,false);
//                    PrintWriter pw = new PrintWriter(fos);
//                    writeFile(hotel,pw);
                    System.exit(0);
                default:
                    System.out.println("Invalid input.Try again");
            }
        }
    }


}

