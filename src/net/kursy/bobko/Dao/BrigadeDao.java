package net.kursy.bobko.Dao;

import net.kursy.bobko.container.Conteiner;
import net.kursy.bobko.managercommands.ManagerRequest;
import net.kursy.bobko.entity.Brigade;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrigadeDao extends AbstractDao implements ItemDao<Brigade> {

    private static final Logger logger = Logger.getLogger(BrigadeDao.class);
    private Conteiner executeBrigade(ResultSet rs) {

        Conteiner conteiner = new Conteiner(new ArrayList<>());
        try {
            while (rs.next()) {
                Brigade brigade = new Brigade();
                brigade.setId(rs.getInt(1));
                brigade.setName(rs.getString(2));
                brigade.setActive(rs.getBoolean(3));
                conteiner.add(brigade);
            }
        } catch (SQLException e) {
            logger.error("Error work with BrigadeDataBaseDAO" + e);
        }
        return conteiner;
    }

    @Override
    public Brigade readById(int id) {
        Connection connection = null;
        Brigade brigade = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            statement = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("GET_BRIGADE_BY_ID"));
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Conteiner conteiner = executeBrigade(rs);
            if (conteiner.getLenght() > 0) {
                brigade = (Brigade) conteiner.getEntity();
            }

        } catch (SQLException e) {
            logger.error("Error work with request GET_BRIGADE_BY_ID" + e);
        } finally {
            closeConnection(connection);
        }
        return brigade;
    }

    @Override
    public int update(Brigade t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int count = 0;

        try {

            connection = getConnection();
            statement = connection.prepareStatement( ManagerRequest.getInstance()
                    .getSqlCommand("UPDATE_BRIGADE"));
            statement.setInt(1, t.getId());
            statement.setString(2, t.getName());
            statement.setBoolean(3, t.isActive());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception in BrigadeDatabaseDAO update" + e);
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
                    .getSqlCommand("GET_ALL_BRIGADE"));
            rs = ps.executeQuery();
            conteiner = executeBrigade(rs);
        } catch (SQLException e) {
            logger.error("Error work with BrigadeDAO " + e);
        } finally {
            closeConnection(connection);
        }
        return conteiner;
    }
}
