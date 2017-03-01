package com.company;

import org.joda.time.DateTime;

/**
 * Created by nashm on 01/03/2017.
 */
public class Restaurant {
    private Table[] el, l, m, s;
    public int defaultReservationTime;
    public DateTime openingTime;
    public DateTime closingTime;

    public Restaurant(){
        el = new Table[1];
        l = new Table[3];
        m = new Table[8];
        s = new Table[4];
        openingTime = new DateTime(2017,3,1,11,0);
        closingTime = new DateTime(2017,3,1,22,0);

        defaultReservationTime = 2;
    }
    public Restaurant(int noOfEl, int noOfl, int noOfm, int noOfs, int drT, DateTime ot, DateTime ct){
        el = new Table[noOfEl];
        l = new Table[noOfl];
        m = new Table[noOfm];
        s = new Table[noOfs];
        openingTime = ot;
        closingTime = ct;

        int x=0;
        for (Table i : el) {
            i.size = 12;
            i.id=x;
            x++;
        }

        for (Table i : l) {
            i.size = 6;
            i.id=x;
            x++;
        }

        for (Table i : m) {
            i.size = 4;
            i.id=x;
            x++;
        }

        for (Table i : s) {
            i.size = 2;
            i.id=x;
            x++;
        }

        defaultReservationTime = drT;
    }
}
