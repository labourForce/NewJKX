import net.kursy.bobko.Dao.WorkDao;
import net.kursy.bobko.container.Conteiner;
import net.kursy.bobko.entity.Work;
import net.kursy.bobko.managercommands.ManagerDb;
import net.kursy.bobko.managercommands.ManagerRequest;

/**
 * Created by TimeLine on 20.12.2016.
 */
public class Test {
    public static void main(String[] args) {

            System.out.println(ManagerDb.getInstance().getProperty("LOGIN"));
        System.out.println(ManagerRequest.getInstance().getSqlCommand("UPDATE_USER"));
        }
    }

