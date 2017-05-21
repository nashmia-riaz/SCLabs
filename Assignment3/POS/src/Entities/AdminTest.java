package Entities;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by nashm on 21/05/2017.
 */
@RunWith(Arquillian.class)
public class AdminTest {
    private Admin a;
    @Before public void onlyOnce(){
        a = new Admin("username", "1234");
    }
    @org.junit.Test
    public void getUsername() throws Exception {
        assertEquals("username", a.getUsername());
    }

    @org.junit.Test
    public void setUsername() throws Exception {
        a.setUsername("pope101");
        assertEquals("pope101", a.getUsername());
    }

    @org.junit.Test
    public void getPassword() throws Exception {
        assertEquals("1234", a.getPassword());
    }

    @org.junit.Test
    public void setPassword() throws Exception {
        a.setPassword("pwrofchrist");
        assertEquals("pwrofchrist", a.getPassword());
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Admin.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
