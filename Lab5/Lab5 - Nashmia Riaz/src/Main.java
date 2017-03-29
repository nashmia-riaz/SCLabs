import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.io.File;
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

//        List<areaLocation> locations = new List<areaLocation>;
        Scanner scanner = new Scanner(new File("src/GeoLiteCity-Location.csv"));
        scanner.useDelimiter("\n");
        String a = new String();
        manageLocations ml = new manageLocations();
        int i =0;
        while(scanner.hasNext() ){
            if(i>1) {
                a=scanner.next();
                String temp[] = a.split("[\\\\,\\\\s]+");
                int id =Integer.parseInt(temp[0]);
                String country = temp[1];
                country = country.replace("\"","");
                Float lat = Float.parseFloat(temp[5]);
                Float lon =  Float.parseFloat(temp[6]);

                System.out.println(id + country + lat + lon+"endl");
                ml.addEntry(country, lat, lon);

            }
            else
                scanner.next();
            i++;
        }
        scanner.close();

        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }
}