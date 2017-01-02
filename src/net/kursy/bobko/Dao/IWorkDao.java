package net.kursy.bobko.Dao;


import net.kursy.bobko.container.Conteiner;
import net.kursy.bobko.entity.Work;

public interface IWorkDao extends ItemDao<Work> {

    Conteiner getByIdUser(int id);

    int create(Work work);

    int createByIdEntity(int idUser, int idTypeOfWork, int idScopeOfWork,
                         String address, String date, String status);

    Work getWork(int idUser, int idTypeOfWork, int idScopeOfWork,
                 String address, String date, String status);

    Conteiner<Work> getAllWorksForUser(int id);

    Conteiner<Work> getALLWorksByProcessing(String status);

    int updateStatus(Work work);

}
