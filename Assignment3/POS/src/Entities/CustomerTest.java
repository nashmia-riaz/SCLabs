package Entities;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by nashm on 21/05/2017.
 */
@RunWith(Arquillian.class)
public class CustomerTest {
    private Customer c;

    @Before
    public void onlyOnce(){
       c = new Customer("C001", "Nashmia", "F", 22);
    }
    @Test
    public void getId() throws Exception {
        assertEquals("C001", c.getId());
    }

    @Test
    public void setId() throws Exception {
        c.setId("C002");
        assertEquals("C002", c.getId());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Nashmia", c.getName());
    }

    @Test
    public void setName() throws Exception {
        c.setName("Riaz");
        assertEquals("Riaz", c.getName());
    }

    @Test
    public void getGender() throws Exception {
        assertEquals("F", c.getGender());
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Customer.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
