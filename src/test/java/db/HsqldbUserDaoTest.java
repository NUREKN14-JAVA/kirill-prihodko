package db;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;

import kn145.prihodko.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		connectionFactory = new ConnectionFactoryImpl();
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
	@Test
	public void testFindAll() {
		try {
			Collection<User> collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 2, collection.size());
			
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	@Test
	public void testFind()	{
		 try {
	            User user = dao.find(1000L);
	            assertNotNull("testFind failed - no user 1000", user.getId());
				assertEquals("testFind failed - no user fullname", "Poroshenko, Petro", user.getFullName());
				assertEquals("testFind failed - no user.getID", new Long(1000), user.getId());
	        } catch (DatabaseException e) {
	            e.printStackTrace();
	            fail(e.toString());
	        }
		
	}
	@Test
	public void testDelete() {
		try {
			User user = dao.find(new Long(1000));
			dao.delete(user);
			user = dao.find(new Long(1000));
			assertNull("User was not deleted", user.getId());
		} catch (DatabaseException de) {
			de.printStackTrace();
			fail(de.toString());
		}
	}
	@Test
	public void testUpdate() {
		Long testing_id = new Long(1000);
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(1997, Calendar.MAY, 17);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			User user = new User();
			user.setFirstName("Kirill");
			user.setLastName("Prihodko");
			user.setDateOfBirthd(calendar.getTime());
			user.setId(testing_id);
			dao.update(user);
			User updated_user = dao.find(testing_id);
			assertNotNull("testUpdate failed - no user 1000", updated_user.getId());
			assertEquals("testUpdate failed - full name doesnt match", user.getFullName(), updated_user.getFullName());
			assertEquals("testFind failed - DoB doesnt match ",format1.format(calendar.getTime()),format1.format(updated_user.getDateOfBirthd().getTime()));
		}
		catch(DatabaseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl();
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	
	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataset = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataset;
	}

}
