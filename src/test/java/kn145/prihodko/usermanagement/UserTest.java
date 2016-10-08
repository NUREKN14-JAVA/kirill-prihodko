package kn145.prihodko.usermanagement;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import kn145.prihodko.usermanagement.User;

public class UserTest extends TestCase {
	private User user;
	private Date dateOfBirthd;

	public void setUp() throws Exception {
		super.setUp();
		user = new User();
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(1984, Calendar.MAY, 26);
		dateOfBirthd = calendar.getTime();
	}

	public void testGetFullName() {
		user.setFirstName("Kirill");
		user.setLastName("Prihodko");
		assertEquals("Prihodko, Kirill", user.getFullName());
	}
	
	public void testGetAge()
	{
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(2016 - 1984, user.getAge());
	}
}
