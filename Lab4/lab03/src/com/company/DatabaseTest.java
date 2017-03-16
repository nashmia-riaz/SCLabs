package com.company;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nashm on 15/03/2017.
 */
public class DatabaseTest {
    Database db = new Database();
    @Test
    public void authenticate() throws Exception {
    boolean x = db.authenticate("nashmia","123456","customers");
    assertEquals(true, x);

    x = db.authenticate("nashmia","hello","admin");
    assertEquals(true, x);


    x = db.authenticate("nashmia","hello1","admin");
    assertEquals(false, x);


    }

    @Test
    public void insertReservations() throws Exception {
        Customer c = new Customer("Nashmia", 123);
        Table t = new Table (6, 2);
        DateTime st = new DateTime(2017,3,1,12,0);
        DateTime et = new DateTime(2017,3,1,14,0);
        boolean x = db.insertReservations(c, t, st, et, 0);
        assertEquals(true, x);
    }

    @Test
    public void retrieveReservations() throws Exception {
        boolean x = db.retrieveReservations();
        assertEquals(true, x);
    }

    @Test
    public void cancelReservation() throws Exception {
        boolean x = db.cancelReservation(2);
        assertEquals(true, x);
    }

}