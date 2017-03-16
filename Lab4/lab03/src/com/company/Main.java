package com.company;

import org.joda.time.DateTime;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
	// write your code here
    Restaurant myRestaurant = new Restaurant();
    ReservationManager myRM = new ReservationManager();
    Database db = new Database();
    boolean exit = false;

    int option = 0;
    Scanner scanner = new Scanner(System.in);
    System.out.println("*********WELCOME TO THE RESTAURANT RESERVATION SYSTEM*********");
    while (!exit){
        System.out.println("Please do one of the following. Press\n" +
                "1. View current reservations\n"+
                "2. Make a reservation\n"+
                "3. Cancel a reservation\n"+
                "4. Exit\n");

        option = scanner.nextInt();
        int rID =0;

        switch(option){
            case 1:
                System.out.println("Please enter username and password to proceed");
                String uname = scanner.next();
                String upword = scanner.next();
                boolean doesUserExist = db.authenticate(uname, upword, "admin");
                if(doesUserExist)
                    db.retrieveReservations();
                else
                    System.out.println("User does not exist!");
                break;

            case 2:
                System.out.println("Please enter username and password to proceed");
                uname = scanner.next();
                upword = scanner.next();
                doesUserExist = db.authenticate(uname, upword, "customers");
                if(doesUserExist){
                    System.out.println("To make a reservation, enter Customer details:\nName");
                    String n = scanner.next();
                    System.out.println("Phone number");
                    int pno = scanner.nextInt();
                    Customer customer = new Customer(n,pno);

                    System.out.println("Enter table details\nTable size");
                    int s = scanner.nextInt();
                    System.out.println("Table id");
                    int id = scanner.nextInt();
                    Table table = new Table(s, id);

                    System.out.println("Enter reservation start time hour");
                    int stH = scanner.nextInt();
                    System.out.println("Enter reservation start time minute");
                    int stM = scanner.nextInt();

                    System.out.println("Enter reservation end time hour");
                    int etH = scanner.nextInt();
                    System.out.println("Enter reservation end time minute");
                    int etM = scanner.nextInt();

                    DateTime st = new DateTime(2017,3,1,stH,stM);
                    DateTime et = new DateTime(2017,3,1,etH,etM);

                    if(myRM.makeReservation(customer, table, st, et, rID))
                        rID++;
                }
                else
                    System.out.println("User does not exist!");

                break;

            case 3:
                System.out.println("Please enter username and password to proceed");
                uname = scanner.next();
                upword = scanner.next();
                doesUserExist = db.authenticate(uname, upword, "admin");
                if(doesUserExist){
                    System.out.println("Enter reservation ID to cancel reservation");
                    int rid = scanner.nextInt();
                    boolean x =myRM.cancelReservation(rid);
                }
                else
                    System.out.println("User does not exist!");
                break;

            case 4:
                exit = true;
                break;

        }
    }


    }
}
