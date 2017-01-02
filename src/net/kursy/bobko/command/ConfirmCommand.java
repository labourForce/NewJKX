package net.kursy.bobko.command;

import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.services.StatusChange;

import javax.servlet.http.HttpServletRequest;


public class ConfirmCommand implements Command {

    private StatusChange statusChange;

    public ConfirmCommand(StatusChange statusChange) {
        this.statusChange = statusChange;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("idWork");
        String confirm = request.getParameter("confirm");
        if ( id == null){
            return ConstForJsp.ERROR_PAGE;
        }
        int idWork = Integer.parseInt(id);
        return statusChange.changeStatus( idWork, confirm, request);
    }
}
