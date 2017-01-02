package net.kursy.bobko.services;


import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.Dao.UserDao;
import net.kursy.bobko.Dao.WorkDao;
import net.kursy.bobko.container.ContainerForJsp;
import net.kursy.bobko.container.Conteiner;
import net.kursy.bobko.entity.User;
import net.kursy.bobko.entity.Work;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class LogIn {

    private static final Logger logger = Logger.getLogger(LogIn.class);

    private User user;
    private Work work;

    private final String STATUS = "processing";

    public LogIn() {
    }

    public String logIn(HttpServletRequest request, String username, String password) {
        String page = null;
        user = new UserDao().getUserByLoginAndPass(username, password);

        if (user == null) {
            page = ConstForJsp.LOGIN_PAGE;
        } else if ("user".equals(user.getRole().getValue())) {
            request.getSession(true);
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("role", user.getRole().getValue());
            ContainerForJsp.typeOfWorkAsList(request);
            ContainerForJsp.scopeOfWorkAsList(request);
            page = ConstForJsp.USER_PAGE;
        } else if ("admin".equals(user.getRole().getValue())) {
            request.getSession(true);
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("role", user.getRole().getValue());
            ContainerForJsp.allWorkByProc(request, STATUS);
            page = ConstForJsp.ADMIN_PAGE;
            return page;
        }
        return page;
    }
}
