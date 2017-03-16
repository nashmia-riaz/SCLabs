package com.company;

import java.sql.*;
import com.mysql.jdbc.*;
import com.mysql.jdbc.Driver;
import org.joda.time.DateTime;

/**
 * Created by nashm on 15/03/2017.
 */
public class Database
{
    private Connection conn;
    private Statement stmt;

    Database(){
        try
        {
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName); // here is the ClassNotFoundException

            // Step 2: Establish the connection to the database
            String url = "jdbc:mysql://localhost/Restaurant";
            conn = DriverManager.getConnection(url,"root","");
            System.out.println("Connected to the database successfully");
            stmt = conn.createStatement();
        }
        catch (Exception e)
        {
            System.err.println("D'oh! Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public boolean authenticate(String uname, String pword, String table) throws SQLException {
       String sql = "SELECT `name`, `password` FROM `"+table+"` WHERE name='"+uname+"' AND password='"+pword+"'";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            String name = null;
            String password = null;
            while(rs.next()){
                name = rs.getString("name");
                password = rs.getString("password");
            }
            if((name!=null) && (password!=null))
                return true;
            else
                return false;
//            System.out.println("username is "+rs.getString("c_name"));
//            System.out.println("username is "+rs.getString("c_password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertReservations(Customer customer, Table table, DateTime st, DateTime et, int ReservationID){
        String name = customer.name;
        int tableNo = table.id;
        String startTime = st.getHourOfDay() + ":"+st.getMinuteOfHour();
        String endTime = et.getHourOfDay() + ":"+et.getMinuteOfHour();

        String sql = "INSERT INTO `reservations`(`name`, `tableNo`, `startTime`, `endTime`) VALUES ('"+name+"',"+tableNo+",'"+startTime+"','"+endTime+"')";
        System.out.println(sql);
        try {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean retrieveReservations(){
        String sql = "SELECT * FROM `reservations` WHERE 1";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            String name = null;
            int tableNo = 0;
            String startTime = null;
            String endTime = null;
            int ReservationID = 0;
            System.out.format("%s%20s%20s%20s%20s\n", new String("Customer Name"), new String("Table ID"), new String("Start Time"), new String("End Time"), new String("Reservation ID"));


            while(rs.next()){
                name = rs.getString("name");
                tableNo = rs.getInt("tableNo");
                startTime = rs.getString("startTime");
                endTime = rs.getString("endTime");
                ReservationID = rs.getInt("reservationID");

                System.out.format("%s%20s%20s%20s%20s\n", name, tableNo, startTime, endTime, ReservationID);

            }
            if((name!=null) && (startTime!=null) && endTime!=null)
                return true;
            else
                return false;
//            System.out.println("username is "+rs.getString("c_name"));
//            System.out.println("username is "+rs.getString("c_password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelReservation(int rid){
        int reservationId = rid;

        String sql = "DELETE FROM `reservations` WHERE reservationID="+rid;
        System.out.println(sql);
        try {
            stmt.executeUpdate(sql);
            System.out.println("Reservation deleted");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
