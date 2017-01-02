package net.kursy.bobko.Dao;


import net.kursy.bobko.container.Conteiner;
import net.kursy.bobko.managercommands.ManagerRequest;
import net.kursy.bobko.entity.ScopeOfWorks;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScopeOfWorksDao extends AbstractDao implements ItemDao<ScopeOfWorks> {

    private static final Logger logger = Logger.getLogger(ScopeOfWorksDao.class);
    private Conteiner executeScopeOfWorks(ResultSet rs) {
        Conteiner conteiner = new Conteiner(new ArrayList<>());
        try {
            while (rs.next()) {
                ScopeOfWorks scopeOfWorks = new ScopeOfWorks();
                scopeOfWorks.setId(rs.getInt(1));
                scopeOfWorks.setScope(rs.getString(2));
                conteiner.add(scopeOfWorks);
            }
        } catch (SQLException e) {
            logger.error("Error work with ScopeofworksDAO" + e);
        }
        return conteiner;
    }

    @Override
    public ScopeOfWorks readById(int id) {
        Connection connection = null;
        ScopeOfWorks scopeOfWorks = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            statement = connection.prepareStatement( ManagerRequest.getInstance()
                    .getSqlCommand("GET_SCOPE_BY_ID"));
            statement.setInt(1, id);
            rs = statement.executeQuery();
            Conteiner conteiner = executeScopeOfWorks(rs);
            if (conteiner.getLenght() > 0) {
                scopeOfWorks = (ScopeOfWorks) conteiner.getEntity();
            }

        } catch (SQLException e) {
            logger.error("Error work with request GET_TYPE_OF_WORKS_BY_ID" + e);
        } finally {
            closeConnection(connection);
        }
        return scopeOfWorks;
    }

    @Override
    public int update(ScopeOfWorks t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int count = 0;

        try {

            connection = getConnection();
            statement = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("UPDATE_SCOPE"));
            statement.setInt(1, t.getId());
            statement.setString(2, t.getScope());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception in ScopeofworksDatabaseDAO update" + e);
        } finally {
            closeConnection(connection);
        }

        return count;
    }

    @Override
    public Conteiner getAll() {
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        Conteiner conteiner = null;
        try {

            connection = getConnection();
            ps = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("GET_ALL_SCOPE"));
            rs = ps.executeQuery();
            conteiner = executeScopeOfWorks(rs);
        } catch (SQLException e) {
            logger.error("Error work with ScopeofworksDAO " + e);
        } finally {
            closeConnection(connection);
        }
        return conteiner;

    }
}
