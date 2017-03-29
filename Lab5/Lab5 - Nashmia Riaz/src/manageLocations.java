import org.hibernate.*;
import org.hibernate.cfg.Configuration;

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
    public int addEntry(String co, float la, float lo){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;
        try{
            tx = session.beginTransaction();
            areaLocation al = new areaLocation(co, la, lo);
            id = (Integer) session.save(al);
            tx.commit();
        }
        catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return id;
    }


}
