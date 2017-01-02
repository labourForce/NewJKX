package net.kursy.bobko.command;


import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.holder.Holder;
import net.kursy.bobko.services.LogIn;

import javax.servlet.http.HttpServletRequest;


public class LoginCommand implements Command {
    private LogIn logIn;

    public LoginCommand(LogIn logIn) {
        this.logIn = logIn;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null && password == null){
            return ConstForJsp.LOGIN_PAGE;
        }
        return logIn.logIn(request, username, password);
    }
}
