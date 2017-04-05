import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by nashm on 29/03/2017.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();

        System.out.print("What would you like to do?\n"+
        "1. Find latitudes and longitudes of a city.\n"+
        "2. Find the distance between 2 cities.\n"+
        "3. Find 5 places near a city.\n");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        manageLocations ml = new manageLocations();


//        String csvFile = "src/GeoLiteCity-Location.csv";
//        BufferedReader br = null;
//        String line = "";
//        String cvsSplitBy = ",";
//        String[] Line;
//        String[] temp;
        try {
//            br = new BufferedReader(new FileReader(csvFile));
//            int lineNo = 0;
//            while ((line = br.readLine()) != null){
//                lineNo++;
//
//                if(lineNo >2){
//                    temp = line.split(cvsSplitBy);
//                    System.out.println(line);
//                    if(temp.length <=7){
//                        int id =Integer.parseInt(temp[0]);
//                        String country = temp[1].replace("\"", "");
//                        String region = temp[2].replace("\"", "");
//                        String city = temp[3].replace("\"", "");
//                        String postalCode = temp[4].replace("\"", "");
//                        float lon = Float.parseFloat(temp[5]);
//                        float lat = Float.parseFloat(temp[6].replace("\"", ""));
//
//                        ml.addEntry7(id, country, region, city, postalCode, lat, lon);
//                    }
//                    else {
//                        int id =Integer.parseInt(temp[0]);
//                        String country = temp[1].replace("\"", "");
//                        String region = temp[2].replace("\"", "");
//                        String city = temp[3].replace("\"", "");
//                        String postalCode = temp[4].replace("\"", "");
//                        float lon = Float.parseFloat(temp[5]);
//                        float lat = Float.parseFloat(temp[6].replace("\"", ""));
//
//                        int metroCode;
//                        if(temp[7].length() > 0)
//                            metroCode = Integer.parseInt(temp[7]);
//                        else
//                            metroCode = 0;
//
//                        int areaCode=0;
//                        if(temp.length > 8){
//                            if(temp[8].length() > 0)
//                                areaCode = Integer.parseInt(temp[8]);
//                            else
//                                areaCode=0;
//                        }
//
//
//                        ml.addEntry9(id, country, region, city, postalCode, lat, lon, metroCode, areaCode);
//                    }
//                }
//
//            }

            switch (option){
                case 1:
                    System.out.println("Please enter the name of the city.");
                    scanner.nextLine();
                    String city = scanner.nextLine();
                    System.out.println("CITY "+city);
                    Float[] coordinates = ml.findLocation(city);
                    System.out.println("The coordinates of "+city+" are:\nLatitude: "+coordinates[0]+"\nLongitude: "+coordinates[1]);
                    break;

                case 2:
                    System.out.println("Please enter the name of the first city.");
                    scanner.nextLine();
                    String city1 = scanner.nextLine();
                    System.out.println("CITY 1 "+city1);


                    System.out.println("Please enter the name of the second city.");
                    String city2 = scanner.nextLine();
                    System.out.println("CITY 2 "+city2);

                    double distance = ml.findDistance(city1, city2);
                    System.out.println("The distance between the two cities is: "+distance);
                    break;

                case 3:
                    System.out.println("Please enter the name of the city.");
                    scanner.nextLine();
                    String c = scanner.nextLine();
                    System.out.println("CITY "+c);
                    String nearestCity = ml.findNearestCity(c);
                    System.out.println("The city nearest to "+c+" is "+nearestCity);
                    break;

                case 0:
                    break;
            }

            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
//                    System.out.println("  " + o);
                }
            }

        } finally {
            session.close();
        }
    }
}