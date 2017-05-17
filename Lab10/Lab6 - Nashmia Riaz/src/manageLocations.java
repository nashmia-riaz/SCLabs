import com.google.gson.JsonObject;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.lightcouch.CouchDbClient;
import org.lightcouch.DesignDocument;
import org.lightcouch.Response;

import java.util.Iterator;
import java.util.List;

/**
 * Created by nashm on 29/03/2017.
 */
public class manageLocations {
    private CouchDbClient dbClient;
    private List<areaLocation> list;


    manageLocations(){
        dbClient = new CouchDbClient("coordinates", true, "http", "127.0.0.1", 5984, "nash","1234");
        System.out.println("retrieving database...");
        DesignDocument designDoc;
        designDoc = dbClient.design().getFromDesk("example");
        Response response;
        response = dbClient.design().synchronizeWithDb(designDoc);
//        list = dbClient.view("example/by_all")
//
//                .includeDocs(true)
//                .query(areaLocation.class);

    }

    public int addEntry7(int id, String co, String re, String ci, String pc, float lat, float lon){
        areaLocation al = new areaLocation(id, co, re, ci, pc, lat, lon);
        Response response = dbClient.save(al);
        System.out.print(response);
        return 1;
    }

    public int addEntry9(int id, String co, String re, String ci, String pc, float lat, float lon, int mc, int ac){
        areaLocation al = new areaLocation(id, co, re, ci, pc, lat, lon, mc, ac);
        Response response = dbClient.save(al);
        System.out.print(response);
        return 1;
    }

    /* Method to  find a location */
    public Float[] findLocation(String a){
        System.out.println(a);
        areaLocation tmp = new areaLocation();
        Float[] coordinates = new Float[2];

        list = dbClient.view("example/by_city")
                .key(a)
                .limit(1)
                .includeDocs(true)
                .query(areaLocation.class);

        for(int i =0; i< list.size(); i++)
            System.out.println(list.get(i).getCity());

        coordinates[0]=list.get(0).getLatitude();
        coordinates[1]=list.get(0).getLongitude();
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

        Transaction tx = null;
        Float[] coordinates = new Float[2];
        String city2name = "none";
        double distance = 1000;
        try{
//            tx = session.beginTransaction();
//            String hql = "SELECT E.city FROM areaLocation E";
//            List cities = session.createQuery(hql).list();
//            Iterator iterator = cities.iterator();
//            while(iterator.hasNext())
//            {
//                String location = (String) iterator.next();
//                city2name = location;
//                double newdistance = findDistance(city, city2name);
//                if(newdistance<distance)
//                    distance=newdistance;

//                System.out.println(coordinates[0]+" "+coordinates[1]);
//            }
//            tx.commit();
        }catch (HibernateException e) {

        }finally {
//
        }

        return "nothing";
    }
}
