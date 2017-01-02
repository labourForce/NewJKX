package net.kursy.bobko.command;


import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.container.ContainerForJsp;
import net.kursy.bobko.holder.Holder;
import net.kursy.bobko.services.LogOut;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command{

    private LogOut logOut;

    public LogOutCommand(LogOut logOut) {
        this.logOut = logOut;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("username") == null && request.getSession()
                .getAttribute("role") == null){
            return ConstForJsp.LOGIN_PAGE;
        }
        return logOut.logOut(request);
    }
}
