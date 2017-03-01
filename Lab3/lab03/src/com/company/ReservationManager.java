package com.company;

import org.joda.time.DateTime;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nashm on 01/03/2017.
 */
public class ReservationManager extends Restaurant {
    public ArrayList<Reservation> reservationsList;

    public ReservationManager(){
        reservationsList = new ArrayList<>();
    }

    public boolean makeReservation(Customer customer, Table table, DateTime st, DateTime et, int ReservationID){
        for (Reservation i : reservationsList){
            //first check if table is free in that time slot by checking if there exists a reservation of that same table during that exact time slot
            if(table.id == i.table.id && ((st.isAfter(i.startTime) && st.isBefore(i.endTime)) || (et.isAfter(i.startTime) && et.isBefore(i.endTime)) ||
                    st.getHourOfDay() == i.startTime.getHourOfDay() && et.getHourOfDay() == i.endTime.getHourOfDay() &&
                    st.getMinuteOfHour() == i.startTime.getMinuteOfHour() && et.getMinuteOfHour() == i.endTime.getMinuteOfHour())){
                System.out.println("Cannot make reservation. Table is already booked in slot.");
                return false;
            }
            else if(st.isAfter(closingTime) || st.isBefore(openingTime) || et.isAfter(closingTime) || et.isBefore(openingTime)){
                System.out.println("Cannot make reservation. Please pick reservation during restaurant hours.");
                return false;
            }
        }

        if(table.size>6 && table.size<=12){ //reserving the extra large table
            Reservation newReservation = new Reservation(customer, table, st, et);
            reservationsList.add(newReservation);
            System.out.println("Reservation made successfully");
        }
        if(table.size<=6){ //reserving any medium to small table
            et = st.plusHours(2);
            Reservation newReservation = new Reservation(customer, table, st, et);
            reservationsList.add(newReservation);
            System.out.println("Reservation made successfully");
        }

        return true;
    }

    public void viewReservations(){
        if( reservationsList.size()==0){
            System.out.println("No reservations made yet");
        }
        else {
            System.out.println("The following reservations are made so far for today:");
            System.out.format("%s%20s%20s%20s%20s\n", new String("Customer Name"), new String("Table ID"), new String("Start Time"), new String("End Time"), new String("Reservation ID"));
            for (Reservation i : reservationsList) {
                System.out.format("%s%20s%20s:%1s%20s:%1s%20s\n", i.customer.name, i.table.id, i.startTime.getHourOfDay(), i.startTime.getMinuteOfHour(), i.endTime.getHourOfDay(),i.endTime.getMinuteOfHour(), i.reservationID);
            }
        }
    }

    public boolean cancelReservation(int id) {
       for (int i =0; i<reservationsList.size(); i++){
            if(reservationsList.get(i).reservationID == id){
                reservationsList.remove(i);
                System.out.print("Reservation with id "+id+" cancelled");
                return true;
            }
       }

        System.out.print("No reservation with the ID found");
        return false;
    }
}
