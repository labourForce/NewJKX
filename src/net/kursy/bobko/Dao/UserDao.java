package net.kursy.bobko.Dao;


import net.kursy.bobko.container.Conteiner;
import net.kursy.bobko.managercommands.ManagerRequest;
import net.kursy.bobko.entity.User;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao extends AbstractDao implements ItemDao<User>, IUserDao<User> {

    private static final Logger logger = Logger.getLogger(UserDao.class);

    private Conteiner executeUser(ResultSet rs) {
        Conteiner conteiner = new Conteiner(new ArrayList<>());
        RoleDao roleDao = new RoleDao();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRole(roleDao.readById(rs.getInt(4)));
                user.setFirstName(rs.getString(5));
                user.setSecondName(rs.getString(6));
                user.seteMail(rs.getString(7));
                conteiner.add(user);
            }
        } catch (SQLException e) {
            logger.error("Error exequetUser for SQL" + e);
        }
        return conteiner;
    }

    @Override
    public int update(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        int count = 0;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(ManagerRequest.getInstance().
                    getSqlCommand("UPDATE_USER"));
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRole().getId());
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getSecondName());
            statement.setString(7, user.geteMail());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception in UserDatabaseDAO update" + user + e);
        } finally {
            closeConnection(connection);
        }

        return count;
    }

    @Override
    public User readById(int id) {
        Connection connection = null;
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            connection = getConnection();
            ps = connection.prepareStatement( ManagerRequest.getInstance()
                    .getSqlCommand("GET_USER_BY_ID"));
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Conteiner conteiner = executeUser(rs);
            if (conteiner.getLenght() > 0) {
                user = (User) conteiner.getEntity();
            }
        } catch (SQLException e) {
            logger.error("User not found" + user);
        } finally {
            closeConnection(connection);
        }
        return user;
    }

    @Override
    public Conteiner getAll() {
        Connection connection = null;
        Conteiner conteiner = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            connection = getConnection();
            ps = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("GET_ALL_USER"));
            rs = ps.executeQuery();
            conteiner = executeUser(rs);
        } catch (SQLException e) {
            logger.error("Error SQL request for GET_ALL_USER command ");

        } finally {
            closeConnection(connection);
        }

        return conteiner;
    }

    @Override
    public User getUserByLoginAndPass(String username, String password) {
        Connection connection = null;
        Conteiner conteiner = null;
        User user = null;
        ResultSet rs = null;


        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("GET_USER_BY_USERNAME_AND_PASS"));
            ps.setString(1, username);
            ps.setString(2, password.toLowerCase());
            rs = ps.executeQuery();
            conteiner = executeUser(rs);
            if (conteiner.getLenght() > 0) {
                user = (User) conteiner.getEntity();
            }
        } catch (SQLException e) {
            logger.error("Error work with GET_BY_EMAIL_PASS" + e);
        }
        finally {
            closeConnection(connection);
        }

        return user;
    }

    @Override
    public User getUserByLogin(String username) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(ManagerRequest.getInstance()
                    .getSqlCommand("GET_USER_BY_LOGIN"));
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            Conteiner<User> conteiner = executeUser(resultSet);
            if (conteiner.getLenght() > 0){
                user = (User) conteiner.getEntity();
            }
        } catch (SQLException e) {
            logger.error("Error work with GetUserByLogin" + e);
        }
        finally {
            closeConnection(connection);
        }
        return user;
    }

    @Override
    public int create(User user) {
        Connection connection = null;
        int mod = 0;
            try {
                connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(ManagerRequest
                        .getInstance().getSqlCommand("CREATE_USER"));
                ps.setInt(1, user.getRole().getId());
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getFirstName());
                ps.setString(5, user.getSecondName());
                ps.setString(6, user.geteMail());
                mod = ps.executeUpdate();
            } catch (SQLException ex) {
                logger.error("ERROR UserDAO update", ex);
            } finally {
                closeConnection(connection);
            }
            return mod;
    }

    @Override
    public int getIdUser(String username) {
        Connection connection = null;
        int id = 0;
        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(ManagerRequest
                    .getInstance().getSqlCommand("GET_ID_USER"));
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            Conteiner<User> conteiner = executeUser(rs);
            if (conteiner!= null) {
                User user = (User) conteiner.getEntity();
                id = user.getId();
            }
        } catch (SQLException ex) {
            logger.error("ERROR UserDAO getById SQL", ex);
            closeConnection(connection);
        }
        return id;
    }
}
