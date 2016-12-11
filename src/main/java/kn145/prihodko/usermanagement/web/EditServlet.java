package kn145.prihodko.usermanagement.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import kn145.prihodko.usermanagement.User;
import kn145.prihodko.usermanagement.db.DaoFactory;
import kn145.prihodko.usermanagement.db.DatabaseException;

public class EditServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("okButton") != null) {
            doOk(req, resp);
            return;
        }
        if (req.getParameter("cancelButton") != null) {
            doCancel(req, resp);
            return;
        }
        showPage(req,resp);
    }

    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    private void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/browse.jsp").forward(req, resp);
        
    }

    private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        try {
            user = getUser(req);
        } catch (ValidationException e1) {
            req.setAttribute("error", e1.getMessage());
            showPage(req,resp);
            return;
        }
        try {
            processUser(user);
        } catch (DatabaseException e) {
            e.printStackTrace();
            new ServletException(e);
        }
        req.getRequestDispatcher("/browse").forward(req, resp);
    }

    private User getUser(HttpServletRequest req) throws ValidationException {
        User user = new User();
        
        String idStr = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dateStr = req.getParameter("date");
        
        // check if the user filled in all the field
        if (firstName == null || firstName.isEmpty()) {
            throw new ValidationException("First name is empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new ValidationException("Last name is empty");
        }
        if (dateStr == null || dateStr.isEmpty()) {
            throw new ValidationException("Date is empty");
        }
        
        if (idStr != null) {
            user.setId(new Long(idStr));
        }
        
        user.setFirstName(firstName);
        user.setLastName(lastName);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            user.setDateOfBirthd(LocalDate.parse(dateStr,formatter));
        }
        catch (DateTimeParseException e) {
            throw new ValidationException("Date format is incorrect");
        }
        return user;
    }

    protected void processUser(User user) throws DatabaseException {
        DaoFactory.getInstance().getUserDao().update(user);
    }

}