package net.kursy.bobko.Dao;



import net.kursy.bobko.container.Conteiner;
import net.kursy.bobko.managercommands.ManagerRequest;
import net.kursy.bobko.entity.TypeOfWorks;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeOfWorksDao extends AbstractDao implements ItemDao<TypeOfWorks> {

    private static final Logger logger = Logger.getLogger(TypeOfWorksDao.class);
    private Conteiner executeTypeOfWorks(ResultSet rs) {
        Conteiner conteiner = new Conteiner(new ArrayList<>());
        try {
            while (rs.next()) {
                TypeOfWorks typeOfWorks = new TypeOfWorks();
                BrigadeDao brigadeDao = new BrigadeDao();
                typeOfWorks.setId(rs.getInt(1));
                typeOfWorks.setType(rs.getString(2));
                typeOfWorks.setCost(rs.getInt(3));
                typeOfWorks.setBrigade(brigadeDao.readById(rs.getInt(4)));
                conteiner.add(typeOfWorks);
            }
        } catch (SQLException e) {
            logger.error("Error work with TypeofworksDataBaseDAO" + e);
        }
        return conteiner;
    }

    @Override
    public TypeOfWorks readById(int id) {
        Connection connection = null;
        TypeOfWorks typeOfWorks = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("GET_TYPE_OF_WORKS_BY_ID"));
            statement.setInt(1, id);
            rs = statement.executeQuery();
            Conteiner conteiner = executeTypeOfWorks(rs);
            if (conteiner.getLenght() > 0) {
                typeOfWorks = (TypeOfWorks) conteiner.getEntity();
            }

        } catch (SQLException e) {
            logger.error("Error work with request GET_TYPE_OF_WORKS_BY_ID" + e);
        } finally {
            closeConnection(connection);
        }
        return typeOfWorks;
    }

    @Override
    public int update(TypeOfWorks t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int count = 0;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("UPDATE_TYPE_OF_WORKS"));
            statement.setInt(1, t.getId());
            statement.setString(2, t.getType());
            statement.setInt(3, t.getCost());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception in TypeofworksDatabaseDAO update" + e);
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
                    .getSqlCommand("GET_ALL_TYPE_OF_WORKS"));
            rs = ps.executeQuery();
            conteiner = executeTypeOfWorks(rs);
        } catch (SQLException e) {
            logger.error("Error work with TypeofworksDAO " + e);
        } finally {
            closeConnection(connection);
        }
        return conteiner;

    }

}
