package com.company;

import org.joda.time.DateTime;


/**
 * Created by nashm on 01/03/2017.
 */
public class Reservation {
    public Customer customer;
    public Table table;
    public int reservationID;
    public DateTime startTime;
    public DateTime endTime;

    public Reservation(Customer c, Table t, DateTime st, DateTime et){
        customer = c;
        table = t;
        startTime = st;
        endTime = et;
    }

    public Reservation(){
        reservationID = 0 ;
    }

}
