/**
 * Created by Taha on 29/03/2017.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageUsers {
    private static SessionFactory factory;



    public static void main(String[] args) {

        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageUsers ME = new ManageUsers();

        Session session = factory.openSession();

        if(session.createQuery("select 1 from Users").setMaxResults(1).list().isEmpty()){
            System.out.println("Database is not populated");
            String csvFile = "src/test_two-anon.csv";
            String line = "";
            String cvsSplitBy = ",";

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                br.readLine();
                br.readLine();
                br.readLine();

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    // use comma as separator
                    String[] userdetails = line.split(cvsSplitBy);
                    String userid = null;
                    String scheme = null;
                    String usersid = null;
                    float totaltime = 0;
                    String totalstate = null;
                    float t1time = 0;
                    String t1state = null;
                    float t2time = 0;
                    String t2state = null;
                    float t3time = 0;
                    String t3state = null;
                    float t4time = 0;
                    String t4state = null;
                    float t5time = 0;
                    String t5state = null;
                    float t6time = 0;
                    String t6state = null;
                    float t7time = 0;
                    String t7state = null;

                    System.out.println(userdetails[0]);
                    try{
                        userid = userdetails[0];
                        scheme= userdetails[2];
                        totaltime= Float.parseFloat(userdetails[3].substring(1,userdetails[3].length()-2));
                        totalstate= userdetails[4];
                        if(userdetails[6].length()>2)
                            t1time= Float.parseFloat(userdetails[6].substring(1,userdetails[6].length()-2));
                        t1state= userdetails[7];
                        if(userdetails[9].length()>2)
                            t2time= Float.parseFloat(userdetails[9].substring(1,userdetails[9].length()-2));
                        t2state= userdetails[10];
                        if(userdetails[12].length()>2)
                            t3time= Float.parseFloat(userdetails[12].substring(1,userdetails[12].length()-2));
                        t3state= userdetails[13];
                        if(userdetails[15].length()>2)
                            t4time= Float.parseFloat(userdetails[15].substring(1,userdetails[15].length()-2));
                        t4state= userdetails[16];
                        if(userdetails[18].length()>2)
                            t5time= Float.parseFloat(userdetails[18].substring(1,userdetails[18].length()-2));
                        t5state= userdetails[19];
                        if(userdetails[21].length()>2)
                            t6time= Float.parseFloat(userdetails[21].substring(1,userdetails[21].length()-2));
                        t6state= userdetails[22];
                        if(userdetails[24].length()>2)
                            t7time= Float.parseFloat(userdetails[24].substring(1,userdetails[24].length()-2));
                        t7state= userdetails[25];

                    }catch(NumberFormatException ex){ // handle your exception
                        ex.printStackTrace();
                    }




                    //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

                    ME.addRound(userid,scheme,totaltime,totalstate,t1time,t1state,t2time,t2state,t3time,t3state,t4time,t4state,t5time,t5state,t6time,t6state,t7time,t7state);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println("Database is already populated");

        System.exit(0);

    }


    // Method to CREATE an employee in the database
    public void addRound(String userid, String scheme, float totaltime, String totalstate, float t1time, String t1state, float t2time, String t2state, float t3time, String t3state, float t4time, String t4state, float t5time, String t5state, float t6time, String t6state, float t7time, String t7state){

        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Users user = new Users(userid,scheme,totaltime,totalstate,t1time,t1state,t2time,t2state,t3time,t3state,t4time,t4state,t5time,t5state,t6time,t6state,t7time,t7state);
            session.save(user) ;
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return;
    }

}
