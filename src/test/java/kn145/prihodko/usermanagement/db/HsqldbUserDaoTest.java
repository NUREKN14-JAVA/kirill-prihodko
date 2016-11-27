package kn145.prihodko.usermanagement.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import org.junit.Test;

import kn145.prihodko.usermanagement.User;

import junit.framework.TestCase;

public class HsqldbUserDaoTest extends DatabaseTestCase  {
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	
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
	
	
	public void testDelete(){
		try{
			
		User user = new User();
		user.setFirstName("Kirill");
		user.setLastName("Prihodko");
		user.setDateOfBirthd(new Date());
		user = dao.create(user);
		Long id = user.getId();
		
		dao.delete(user);
	
		assertNull(dao.find(id));
		}
		catch (DatabaseException e){
			e.printStackTrace();
			fail(e.toString());
		}
	}

	public void testFind(){
		
		try{
			User user = new User ();
			User user1 = new User ();
			
			user.setFirstName("Kirill");
			user.setLastName("Prihodko");
			user.setDateOfBirthd(new Date());
			user = dao.create(user);
			Long id = user.getId();
			user1 = dao.find(id);
			java.sql.Date date = new java.sql.Date(user.getDateOfBirthd().getTime());
			
			assertEquals(user.getFirstName(), user1.getFirstName());	
			assertEquals(user.getLastName(), user1.getLastName());
			assertEquals(user.getId(), user1.getId());
			assertEquals(date.toString(),user1.getDateOfBirthd().toString());
		}catch (DatabaseException e){
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testUpdate(){
		try{
			User user = new User ();
			User user1 = new User ();
			
			user.setFirstName("John");
			user.setLastName("Doe");
			user.setDateOfBirthd(new Date());
			user = dao.create(user);
			Long id = user.getId();
			
			
			user1.setFirstName("Donald");
			user1.setLastName("Trump");
			user1.setDateOfBirthd(new Date());
			user1.setId(id);
			
			dao.update(user1);
			user=dao.find(id);
			java.sql.Date date = new java.sql.Date(user.getDateOfBirthd().getTime());
			
			assertEquals(user.getFirstName(), "Donald");
			assertEquals(user.getLastName(), "Trump");
			assertEquals(user.getDateOfBirthd().toString(),date.toString());
		}catch (DatabaseException e){
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	

	public void testFindAll() {
		try {
			Collection collection = dao.findAll();
			assertNotNull("Collection is null",collection);
			assertEquals("Collection size",2, collection.size());
		} catch (DatabaseException e) {	
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver","jdbc:hsqldb:file:db/usermanagement","sa","");
		return new DatabaseConnection (connectionFactory.createConnection());
	}


	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader()
				.getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}



	

}
