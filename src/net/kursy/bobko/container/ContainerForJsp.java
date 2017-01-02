package net.kursy.bobko.container;


import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.Dao.ScopeOfWorksDao;
import net.kursy.bobko.Dao.TypeOfWorksDao;
import net.kursy.bobko.Dao.UserDao;
import net.kursy.bobko.Dao.WorkDao;
import net.kursy.bobko.entity.ScopeOfWorks;
import net.kursy.bobko.entity.TypeOfWorks;
import net.kursy.bobko.entity.Work;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ContainerForJsp {

    public static void typeOfWorkAsList(HttpServletRequest request) {
        Conteiner<TypeOfWorks> conteiner = new TypeOfWorksDao().getAll();
        List<TypeOfWorks> listOfWork = new ArrayList<>();
        TypeOfWorks typeOfWorks;
        for (int i = 0; i < conteiner.getLenght(); i++) {
            typeOfWorks = (TypeOfWorks) conteiner.getEntity();
            listOfWork.add(typeOfWorks);
        }
        request.setAttribute("listWork", listOfWork);
    }

    public static void scopeOfWorkAsList(HttpServletRequest request) {
        Conteiner<ScopeOfWorks> conteiner = new ScopeOfWorksDao().getAll();
        List<ScopeOfWorks> list = new ArrayList<>();
        ScopeOfWorks scopeOfWorks;
        for (int i = 0; i < conteiner.getLenght(); i++) {
            scopeOfWorks = (ScopeOfWorks) conteiner.getEntity();
            list.add(scopeOfWorks);
        }
        request.setAttribute("scopeWork", list);
    }

    public static void allWorkByProc(HttpServletRequest request, String status){
        Conteiner conteiner = new WorkDao().getALLWorksByProcessing(status);
        List<Work> workListProc = getWorkListProc(conteiner);
        request.setAttribute("worklistproc", workListProc);

    }

    private static List<Work> getWorkListProc(Conteiner<Work> conteiner){
        Work work;
        List<Work> list = new ArrayList<>();
        for (int i = 0; i < conteiner.getLenght(); i++) {
            work = (Work) conteiner.getEntity();
            list.add(work);
        }
        return list;
    }
}
