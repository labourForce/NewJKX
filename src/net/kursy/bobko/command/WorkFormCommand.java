package net.kursy.bobko.command;


import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.container.ContainerForJsp;
import net.kursy.bobko.services.WorkForm;

import javax.servlet.http.HttpServletRequest;

public class WorkFormCommand implements Command {

    private WorkForm workform;

    public WorkFormCommand(WorkForm workForm){
        this.workform = workForm;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String typeOfWork = request.getParameter("typeofwork");
        String scope = request.getParameter("scope");
        String day = request.getParameter("day");
        String address = request.getParameter("address");
        if (typeOfWork == null && scope == null && day == null && address == null){
            ContainerForJsp.typeOfWorkAsList(request);
            ContainerForJsp.scopeOfWorkAsList(request);
            return ConstForJsp.USER_PAGE;
        }
        int idTypeOfWork = Integer.parseInt(typeOfWork);
        int idScopeOfWork = Integer.parseInt(scope);
        return workform.formFilling(idTypeOfWork, idScopeOfWork, address, day, request);
    }
}
