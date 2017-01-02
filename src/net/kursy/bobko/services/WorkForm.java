package net.kursy.bobko.services;

import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.Dao.UserDao;
import net.kursy.bobko.Dao.WorkDao;
import net.kursy.bobko.container.ContainerForJsp;
import net.kursy.bobko.entity.Work;

import javax.servlet.http.HttpServletRequest;

public class WorkForm {
    private final String STATUS = "processing";
    public String formFilling(int typeOfWork, int scope, String address,
                              String date, HttpServletRequest request){
        int idUser = new UserDao().getUserByLogin((String) request.getSession().getAttribute("username")).getId();
        int count = new WorkDao().createByIdEntity(idUser ,typeOfWork, scope, address, date, STATUS );
        Work work = new WorkDao().getWork(idUser, typeOfWork, scope, address, date, STATUS);
        if (work == null){
            ContainerForJsp.typeOfWorkAsList(request);
            ContainerForJsp.scopeOfWorkAsList(request);
            return ConstForJsp.USER_PAGE;
        }
        request.setAttribute("castomer", work.getUser().getFirstName() + " " + work.getUser().getSecondName());
        request.setAttribute("typeofwork", work.getTypeOfWorks().getType());
        request.setAttribute("scopeofwork", work.getScopeOfWorks().getScope());
        request.setAttribute("address", work.getAddress());
        request.setAttribute("date", work.getDate());
        request.setAttribute("status", work.getStatus());
        return ConstForJsp.RESULT_PAGE;
    }
}
