package net.kursy.bobko.services;

import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.Dao.WorkDao;
import net.kursy.bobko.container.Conteiner;
import net.kursy.bobko.entity.Work;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UserAccount {

    public UserAccount() {
    }

    public String accountUser(HttpServletRequest request, int idUser){
        Conteiner conteiner = new WorkDao().getAllWorksForUser(idUser);
        List<Work> workList = getWorkList(conteiner);
        String page;
        if (workList == null){
            page = ConstForJsp.ERROR_PAGE;
            return page;
        }
        request.setAttribute("worklist", workList);
        page = ConstForJsp.ACCOUNT_USER_PAGE;
        return page;
    }

    private List<Work> getWorkList(Conteiner<Work> conteiner){
        Work work;
        List<Work> list = new ArrayList<>();
        for (int i = 0; i < conteiner.getLenght(); i++) {
            work = (Work) conteiner.getEntity();
            list.add(work);
        }
        return list;
    }
}
