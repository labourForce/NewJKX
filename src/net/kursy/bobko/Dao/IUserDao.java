package net.kursy.bobko.Dao;


public interface IUserDao<User> {

    User getUserByLoginAndPass(String username, String password);

    User getUserByLogin(String username);

    int create(User user);

    int getIdUser(String username);

}
