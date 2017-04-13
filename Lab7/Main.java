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


        String csvFile = "src/test_two-anon.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] Line;
        String[] temp;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            int lineNo = 0;
            while ((line = br.readLine()) != null){
                lineNo++;

                if(lineNo >2){
                    temp = line.split(cvsSplitBy);
                    for(int i =0; i< temp.length; i++) {
                        temp[i] = temp[i].replace("\"", "");
                        System.out.println(temp[i]);
                    }
                    }
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