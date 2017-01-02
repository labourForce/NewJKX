package net.kursy.bobko.command;

import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.Dao.UserDao;
import net.kursy.bobko.services.UserAccount;
import javax.servlet.http.HttpServletRequest;

public class AccountCommand implements Command {

    private UserAccount userAccount;

    public AccountCommand(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int idUser = new UserDao().getUserByLogin((String) request.getSession().getAttribute("username")).getId();
        if (idUser ==  0){
            return ConstForJsp.ERROR_PAGE;
        }
        return userAccount.accountUser(request, idUser);
    }
}
