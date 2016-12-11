package kn145.prihodko.usermanagement.db;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testGetUserDao() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        assertNotNull("Dao Factory is null", daoFactory);
        UserDao userDao = daoFactory.getUserDao();
        assertNotNull("User Dao is null", userDao);
        
    }

}