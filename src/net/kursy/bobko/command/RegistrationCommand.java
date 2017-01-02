package net.kursy.bobko.command;


import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.holder.Holder;
import net.kursy.bobko.services.Registration;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private Registration registration;

    public RegistrationCommand(Registration registration) {
        this.registration = registration;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstname");
        String secondName = request.getParameter("secondname");
        String eMail = request.getParameter("email");
        if (username == null && password == null && firstName == null && secondName == null && eMail == null){
            return ConstForJsp.ERROR_PAGE;
        }
        return registration.createNewUser(username, password, firstName, secondName, eMail, request);
    }
}
