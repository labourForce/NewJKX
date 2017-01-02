package net.kursy.bobko.services;


import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.Dao.WorkDao;
import net.kursy.bobko.container.ContainerForJsp;
import net.kursy.bobko.entity.Work;

import javax.servlet.http.HttpServletRequest;

public class StatusChange {

    private final String STATUS = "confirm";

    public String changeStatus(int idWork, String confirm, HttpServletRequest request){
        WorkDao workDao = new WorkDao();
        Work work = workDao.readById(idWork);
        if (work == null){
            ContainerForJsp.allWorkByProc(request, STATUS);
            return ConstForJsp.ADMIN_PAGE;
        }
        if ("confirm".equals(confirm)){
            work.setStatus(STATUS);
        }else {
            work.setStatus("reject");
        }

        int count = workDao.updateStatus(work);
        if (count != 1){
            ContainerForJsp.allWorkByProc(request, STATUS);
            return ConstForJsp.ADMIN_PAGE;
        }
        ContainerForJsp.allWorkByProc(request, STATUS);
        return ConstForJsp.ADMIN_PAGE;
    }
}
