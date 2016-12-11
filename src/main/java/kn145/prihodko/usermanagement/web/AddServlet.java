package kn145.prihodko.usermanagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kn145.prihodko.usermanagement.User;
import kn145.prihodko.usermanagement.db.DaoFactory;
import kn145.prihodko.usermanagement.db.DatabaseException;

public class AddServlet extends EditServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void processUser(User user) throws DatabaseException {
        DaoFactory.getInstance().getUserDao().create(user);
    }
    
    @Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

}