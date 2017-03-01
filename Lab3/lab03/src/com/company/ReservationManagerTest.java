package com.company;

import org.joda.time.DateTime;

import static org.junit.Assert.*;

/**
 * Created by nashm on 01/03/2017.
 */
public class ReservationManagerTest {
    Restaurant myRestaurant = new Restaurant();
    ReservationManager myRM = new ReservationManager();

    @org.junit.Test
    public void makeReservation() throws Exception {

        Customer c = new Customer("Nashmia", 123);
        Table t = new Table (12, 0);
        DateTime st = new DateTime(2017,3,1,12,0);
        DateTime et = new DateTime(2017,3,1,14,0);
        int rid =0;
        boolean r1 = myRM.makeReservation(c, t, st, et, rid);
        System.out.println("test 1: normal reservation");
        assertEquals(true, r1);

        rid++;
        Customer c1 = new Customer("sana", 123);
        Table t1 = new Table (12, 0);
        DateTime st1 = new DateTime(2017,3,1,10,0);
        DateTime et1 = new DateTime(2017,3,1,14,0);
        boolean r2 = myRM.makeReservation(c1, t1, st1, et1, rid);
        System.out.println("test 2: reservation outside of restaurant hours");
        assertEquals(false, r2);

        rid++;
        Customer c2 = new Customer("Nashmia", 123);
        Table t2 = new Table (12, 0);
        DateTime st2 = new DateTime(2017,3,1,12,0);
        DateTime et2 = new DateTime(2017,3,1,14,0);
        boolean r3 = myRM.makeReservation(c2, t2, st2, et2, rid);
        System.out.println("test 3: reservation at the same time");
        assertEquals(false, r3);

        rid++;
        Customer c3 = new Customer("Nashmia", 123);
        Table t3 = new Table (6, 1);
        DateTime st3 = new DateTime(2017,3,1,12,0);
        DateTime et3 = new DateTime(2017,3,1,14,0);
        boolean r4 = myRM.makeReservation(c3, t3, st3, et3, rid);
        System.out.println("test 4: reservation at the same time of different tables");
        assertEquals(true, r4);

        rid++;
        Customer c4 = new Customer("Nashmia", 123);
        Table t4 = new Table (6, 2);
        DateTime st4 = new DateTime(2017,3,1,12,0);
        DateTime et4 = new DateTime(2017,3,1,14,0);
        boolean r5 = myRM.makeReservation(c4, t4, st4, et4, rid);
        System.out.println("test 5: reservation at the same time of different tables of same sizes");
        assertEquals(true, r5);
    }

    @org.junit.Test
    public void cancelReservation() throws Exception {
        int rid=0;
        Customer c = new Customer("Nashmia", 123);
        Table t = new Table (6, 1);
        DateTime st = new DateTime(2017,3,1,12,0);
        DateTime et = new DateTime(2017,3,1,14,0);
        myRM.makeReservation(c, t, st, et, rid);

        boolean r = myRM.cancelReservation(0);
        System.out.println("test 1: reservation with valid id cancelled");
        assertEquals(true, r);

        boolean r1 = myRM.cancelReservation(1);
        System.out.println("test 1: reservation with valid id cancelled");
        assertEquals(false, r1);

    }

}