package net.kursy.bobko.services;


import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.holder.Holder;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogOut {

    private static final Logger logger = Logger.getLogger(LogOut.class);

    public LogOut() {
    }

    public String logOut(HttpServletRequest request){
        request.getSession().setAttribute("username", null);
        request.getSession().setAttribute("role", null);
        String page = ConstForJsp.LOGIN_PAGE;
        return page;
    }
}
