package net.kursy.bobko.Dao;

import net.kursy.bobko.container.Conteiner;
import net.kursy.bobko.managercommands.ManagerRequest;
import net.kursy.bobko.entity.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleDao extends AbstractDao implements ItemDao<Role> {

    private static final Logger logger = Logger.getLogger(RoleDao.class);
    private Conteiner executeRole(ResultSet rs) {
        Conteiner conteiner = new Conteiner(new ArrayList<>());
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt(1));
                role.setValue(rs.getString(2));
                conteiner.add(role);
            }
        } catch (SQLException e) {
            logger.error("Eror work with RoleDAO" + e);
        }
        return conteiner;
    }

    @Override
    public Role readById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Role role = null;
        try {

            connection = getConnection();
            ps = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("GET_ROLE_BY_ID"));
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Conteiner conteiner = executeRole(rs);
            if (conteiner.getLenght() > 0) {
                role = (Role) conteiner.getEntity();
            }

        } catch (SQLException e) {
            logger.error("Error work with RoleDAO" + e);
        }
        finally {
            closeConnection(connection);
        }
        return role;
    }

    @Override
    public int update(Role t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int count = 0;

        try {

            connection = getConnection();
            statement = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("UPDATE_ROLE"));
            statement.setInt(1, t.getId());
            statement.setString(2, t.getValue());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception in RoleDatabaseDAO update" + e);
        }
        finally {
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
                    .getSqlCommand("GET_ALL_ROLE"));
            rs = ps.executeQuery();
            conteiner = executeRole(rs);
        } catch (SQLException e) {
            logger.error("Error work with RoleDAO GET_ALL" + e);
        }
        finally {
            closeConnection(connection);
        }
        return conteiner;
    }
}
