import net.kursy.bobko.ClassForJspPage.ConstForJsp;
import net.kursy.bobko.command.Command;
import net.kursy.bobko.command.FactoryCommand;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Servlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Servlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        String page;
        FactoryCommand factory = FactoryCommand.getInstance();
        String cmd = request.getParameter("command").toUpperCase();

        if (cmd != null) {
            Command command = factory.getCommand(cmd);
            page = command.execute(request);
            try {
                request.getRequestDispatcher(page).forward(request, response);
            } catch (ServletException e) {
                logger.log(Level.ERROR, "Controller if() ServletEx", e);
            } catch (IOException e) {
                logger.log(Level.ERROR, "Controller if() IOEx", e);
            }
        } else {
            page = ConstForJsp.ERROR_PAGE;
            try {
                request.getRequestDispatcher(page).forward(request, response);
            } catch (ServletException e) {
                logger.log(Level.ERROR, "Controller ServletEx else", e);
            } catch (IOException e) {
                logger.log(Level.ERROR, "Controller IOEx else", e);
            }
        }
    }
}






