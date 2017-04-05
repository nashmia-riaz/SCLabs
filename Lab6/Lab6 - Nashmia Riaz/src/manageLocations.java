import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

/**
 * Created by nashm on 29/03/2017.
 */
public class manageLocations {
    private static SessionFactory factory;

    manageLocations(){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public int addEntry7(int id, String co, String re, String ci, String pc, float lat, float lon){
        Session session = factory.openSession();
        Transaction tx = null;
        int id1=0;
        try{
            tx = session.beginTransaction();
            areaLocation al = new areaLocation(id, co, re, ci, pc, lat, lon);
            id1 = (Integer) session.save(al);
            tx.commit();
        }
        catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return id1;
    }

    public int addEntry9(int id, String co, String re, String ci, String pc, float lat, float lon, int mc, int ac){
        Session session = factory.openSession();
        Transaction tx = null;
        int id1=0;
        try{
            tx = session.beginTransaction();
            areaLocation al = new areaLocation(id, co, re, ci, pc, lat, lon, mc, ac);
            id1 = (Integer) session.save(al);
            tx.commit();
        }
        catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return id1;
    }

    /* Method to  find a location */
    public Float[] findLocation(String a){
        Session session = factory.openSession();
        Transaction tx = null;
        Float[] coordinates = new Float[2];
        try{
            tx = session.beginTransaction();
            String hql = "FROM areaLocation E WHERE E.city='"+a+"'";
            List cities = session.createQuery(hql).list();

            Iterator iterator = cities.iterator();
            areaLocation location = (areaLocation) iterator.next();
            coordinates[0] = location.getLatitude();
            coordinates[1] = location.getLongitude();
            while(iterator.hasNext())
            {
                coordinates[0]=location.getLatitude();
                coordinates[1]=location.getLongitude();
                location = (areaLocation) iterator.next();

//                System.out.println(coordinates[0]+" "+coordinates[1]);
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return coordinates;
    }

    public double findDistance(String city1, String city2){
        Float[] city1coords = findLocation(city1);
        Float[] city2coords = findLocation(city2);

        double x1 = Math.toRadians((double) city1coords[0]);
        double y1 = Math.toRadians((double) city1coords[1]);
        double x2 = Math.toRadians((double) city2coords[0]);
        double y2 = Math.toRadians((double) city2coords[1]);

        double a = Math.pow(Math.sin((x2-x1)/2), 2)
                + Math.cos(x1) * Math.cos(x2) * Math.pow(Math.sin((y2-y1)/2), 2);

        // great circle distance in radians
        double angle2 = 2 * Math.asin(Math.min(1, Math.sqrt(a)));

        // convert back to degrees
        angle2 = Math.toDegrees(angle2);

        // each degree on a great circle of Earth is 60 nautical miles
        double distance2 = 60 * angle2;
        return distance2;
    }

    public String findNearestCity(String city){
        Float[] cityCoords = findLocation(city);

        Session session = factory.openSession();
        Transaction tx = null;
        Float[] coordinates = new Float[2];
        String city2name = "none";
        double distance = 1000;
        try{
            tx = session.beginTransaction();
            String hql = "SELECT E.city FROM areaLocation E";
            List cities = session.createQuery(hql).list();
            Iterator iterator = cities.iterator();
            while(iterator.hasNext())
            {
                String location = (String) iterator.next();
                city2name = location;
                double newdistance = findDistance(city, city2name);
                if(newdistance<distance)
                    distance=newdistance;

//                System.out.println(coordinates[0]+" "+coordinates[1]);
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return city2name;
    }
}
