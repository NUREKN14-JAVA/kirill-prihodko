package db;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetUserDAO() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instance is null.", daoFactory);
			UserDAO userDAO = daoFactory.getUserDAO();
			assertNotNull("UserDAO instance is null", userDAO);
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
