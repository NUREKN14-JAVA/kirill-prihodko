package db;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import kn145.prihodko.usermanagement.User;

public class HsqldbUserDaoTest {

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	@Before
	public void setUp() throws Exception {
		this.connectionFactory = new ConnectionFactoryImpl();
		dao = new HsqldbUserDao(connectionFactory);
	}

	@Test
	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("Kirill");
			user.setLastName("Prihodko");
			user.setDateOfBirthd(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
