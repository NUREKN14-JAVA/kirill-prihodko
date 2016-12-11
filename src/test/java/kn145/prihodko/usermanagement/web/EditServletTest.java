package kn145.prihodko.usermanagement.web;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import kn145.prihodko.usermanagement.User;

public class EditServletTest extends MockServletTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }

    @Test
    public void testEdit() {
        LocalDate date = LocalDate.now();
        User user = new User(666L, "Ozzy", "Osbourne", date);
        getMockUserDao().expect("update", user);
        addRequestParameter("id", "666");
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
    }
    
    @Test
    public void testEditEmptyFirstName() {
        LocalDate date = LocalDate.now();
        addRequestParameter("id", "666");
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    @Test
    public void testEditEmptyLastName() {
        LocalDate date = LocalDate.now();
        addRequestParameter("id", "666");
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    @Test
    public void testEditEmptyDate() {
        addRequestParameter("id", "666");
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
        
    }
    @Test
    public void testEditInvalidDate() {
        addRequestParameter("id", "666");
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("date", "42");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
}