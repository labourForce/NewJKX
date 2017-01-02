package net.kursy.bobko.services;


import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.Dao.UserDao;
import net.kursy.bobko.container.ContainerForJsp;
import net.kursy.bobko.entity.Role;
import net.kursy.bobko.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Registration {

    private static final Logger logger = Logger.getLogger(Registration.class);

    private Role role = new Role();


    public String createNewUser(String username, String password, String firstname,
                                String secondname, String email, HttpServletRequest request){
        int count = 0;
        String page = null;
        UserDao userDao =new UserDao();
        User user = userDao.getUserByLogin(username);
        if (user != null){
            page = ConstForJsp.REGISTRATION_PAGE;
        }
        role.setId(1);
        user = new User(role, username, password, firstname, secondname, email);
        count = userDao.create(user);
        if (count != 0){
             page = ConstForJsp.USER_PAGE;
            request.getSession(true);
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("role", user.getRole().getValue());
            ContainerForJsp.typeOfWorkAsList(request);
            ContainerForJsp.scopeOfWorkAsList(request);
        }

        return page;
    }
}
