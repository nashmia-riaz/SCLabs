package dao;

import Entities.Employee;
import Entities.Item;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.lightcouch.CouchDbClient;

import static org.junit.Assert.*;

/**
 * Created by nashm on 21/05/2017.
 */
@RunWith(Arquillian.class)
public class DatabaseDAOTest {
    private DatabaseDAO appDatabase;
    private CouchDbClient db;

    @Before public void onlyOnce(){
        appDatabase = new DatabaseDAO();
        db = new CouchDbClient("inventorycafe1", true, "http", "127.0.0.1", 5984, "nash","1234");

    }

    @org.junit.Test
    public void testDatabase() throws Exception {
        Employee e = new Employee("E020", "Nashmia Riaz", "UUO123", "nashmiariaz", "1234");
        appDatabase.saveEmployee(db, e);
        int del = appDatabase.deleteEmployee(db, e);
        assertEquals(1, del);

        Employee e1 = new Employee("E100","","","","");
        del = appDatabase.deleteEmployee(db,e1);
        assertEquals(0,del);
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DatabaseDAO.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
