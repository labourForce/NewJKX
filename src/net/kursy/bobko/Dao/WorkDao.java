package net.kursy.bobko.Dao;


import net.kursy.bobko.container.Conteiner;
import net.kursy.bobko.managercommands.ManagerRequest;
import net.kursy.bobko.entity.ScopeOfWorks;
import net.kursy.bobko.entity.TypeOfWorks;
import net.kursy.bobko.entity.User;
import net.kursy.bobko.entity.Work;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkDao extends AbstractDao implements ItemDao<Work>, IWorkDao {

    private static final Logger logger = Logger.getLogger(WorkDao.class);

    private Conteiner executeWorkDao(ResultSet rs) {
        Conteiner conteiner = new Conteiner(new ArrayList<>());
        try {
            while (rs.next()) {
                Work work = new Work();
                ScopeOfWorksDao scopeOfWorksDao = new ScopeOfWorksDao();
                UserDao userDao = new UserDao();
                TypeOfWorksDao typeOfWorksDao = new TypeOfWorksDao();
                work.setId(rs.getInt(1));
                User user = userDao.readById(rs.getInt(2));
                work.setUser(user);
                TypeOfWorks typeOfWorks = typeOfWorksDao.readById(rs.getInt(3));
                work.setTypeOfWorks(typeOfWorks);
                ScopeOfWorks scopeOfWorks = scopeOfWorksDao.readById(rs.getInt(4));
                work.setScopeOfWorks(scopeOfWorks);
                work.setAddress(rs.getString(5));
                work.setDate(rs.getString(6));
                work.setStatus(rs.getString(7));
                conteiner.add(work);
            }
        } catch (SQLException e) {
            logger.error("Error work with WorkDAO" + e);
        }
        return conteiner;
    }

    @Override
    public Work readById(int id) {
        Connection connection = null;
        Work work = null;
        ResultSet rs;
        PreparedStatement statement;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("GET_WORK_BY_ID"));
            statement.setInt(1, id);
            rs = statement.executeQuery();
            Conteiner conteiner = executeWorkDao(rs);
            if (conteiner.getLenght() > 0) {
                work = (Work) conteiner.getEntity();
            }

        } catch (SQLException e) {
            logger.error("Error work with request GET_WORK_BY_ID" + e);
        } finally {
            closeConnection(connection);
        }
        return work;
    }

    @Override
    public int update(Work work) {
        Connection connection = null;
        PreparedStatement statement;
        int count = 0;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("UPDATE_ALL_FOR_WORK"));
            statement.setInt(1, work.getUser().getId());
            statement.setInt(2, work.getTypeOfWorks().getId());
            statement.setInt(3, work.getScopeOfWorks().getId());
            statement.setString(4, work.getAddress());
            statement.setString(5, work.getDate());
            statement.setString(6, work.getStatus());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception in WORKDAO update" + e);
        } finally {
            closeConnection(connection);
        }

        return count;
    }

    @Override
    public Conteiner getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conteiner conteiner = null;
        try {

            connection = getConnection();
            ps = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("GET_ALL_WORK"));
            rs = ps.executeQuery();
            conteiner = executeWorkDao(rs);
        } catch (SQLException e) {
            logger.error("Error work with WorkDAO " + e);
        } finally {
            closeConnection(connection);
        }
        return conteiner;

    }

    @Override
    public Conteiner getByIdUser(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conteiner conteiner = null;
        try {

            connection = getConnection();
            ps = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("GET_BY_ID_USER"));
            ps.setInt(1, id);
            rs = ps.executeQuery();
            conteiner = executeWorkDao(rs);
            conteiner = executeWorkDao(rs);
        } catch (SQLException e) {
            logger.error("Error work with WorkDAO " + e);
        } finally {
            closeConnection(connection);
        }
        return conteiner;
    }

    @Override
    public int create(Work work) {
            Connection connection = null;
            int mod = 0;
            try {
                connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(ManagerRequest
                        .getInstance().getSqlCommand("CREATE_WORK"));
                ps.setInt(1, work.getUser().getId());
                ps.setInt(2, work.getTypeOfWorks().getId());
                ps.setInt(3, work.getScopeOfWorks().getId());
                ps.setString(4, work.getAddress());
                ps.setString(5, work.getDate());
                ps.setString(6, work.getStatus());
                mod = ps.executeUpdate();
            } catch (SQLException ex) {
                logger.error("ERROR UserDAO update", ex);
            } finally {
                closeConnection(connection);
            }
            return mod;
        }

    @Override
    public int createByIdEntity(int idUser, int idTypeOfWork, int idScopeOfWork,
                                String address, String date, String status) {
        Connection connection = null;
        int mod = 0;
        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(ManagerRequest
                    .getInstance().getSqlCommand("CREATE_WORK"));
            ps.setInt(1, idUser);
            ps.setInt(2, idTypeOfWork);
            ps.setInt(3, idScopeOfWork);
            ps.setString(4, address);
            ps.setString(5, date);
            ps.setString(6, status);
            mod = ps.executeUpdate();
        } catch (SQLException ex) {
            logger.error("ERROR UserDAO update", ex);
        } finally {
            closeConnection(connection);
        }
        return mod;
    }

    @Override
    public Work getWork(int idUser, int idTypeOfWork, int idScopeOfWork,
                        String address, String date, String status) {
        Connection connection = null;
        Conteiner<Work> conteiner;
        Work work = null;
        PreparedStatement ps;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(ManagerRequest
                    .getInstance().getSqlCommand("GET_WORK"));
            ps.setInt(1, idUser);
            ps.setInt(2, idTypeOfWork);
            ps.setInt(3, idScopeOfWork);
            ps.setString(4, address);
            ps.setString(5, date);
            ps.setString(6, status);
            ResultSet rs = ps.executeQuery();
            conteiner = executeWorkDao(rs);
            if (conteiner.getLenght() > 0){
                work = (Work) conteiner.getEntity();
            }
        } catch (SQLException e) {
            logger.error("Error" + e);
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return work;
    }

    @Override
    public Conteiner<Work> getAllWorksForUser(int id) {
        Connection connection = null;
        Conteiner container = null;
        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(ManagerRequest
                    .getInstance().getSqlCommand("GET_WORK_ALL_BY_ID"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            container = executeWorkDao(rs);
        } catch (SQLException ex) {
            logger.error("ERROR WorkDAO getAllById SQL", ex);
        } finally {
            closeConnection(connection);
        }
        return container;
    }

    @Override
    public Conteiner<Work> getALLWorksByProcessing(String status) {
        Conteiner container = null;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(ManagerRequest
                    .getInstance().getSqlCommand("GET_PROCESSING_WORKS"));
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            container = executeWorkDao(rs);
        } catch (SQLException ex) {
            logger.error("ERROR WorkDAO getAllProc SQL", ex);
        } finally {
            closeConnection(connection);
        }
        return container;
    }

    @Override
    public int updateStatus(Work work) {
            int mod = -1;
        Connection connection = null;
        try {
                connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(ManagerRequest
                        .getInstance().getSqlCommand("UPDATE_STATUS_FOR_WORK"));
                preparedStatement.setString(1, work.getStatus());
                preparedStatement.setInt(2, work.getId());
                mod = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                logger.error("ERROR WorkDAO update", e);
            } finally {
                closeConnection(connection);
            }
            return mod;
        }
    }



