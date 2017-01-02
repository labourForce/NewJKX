package net.kursy.bobko.managercommands;

import org.apache.log4j.Logger;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class ManagerDb {

    private static final Logger logger = Logger.getLogger(ManagerDb.class);
    private ResourceBundle bundle;
    private static ManagerDb instance;
    private static final String DB_CONFIG = "net.kursy.bobko.managercommands.properties.DB";


    public static ManagerDb getInstance() {
        if (instance == null) {
            instance = new ManagerDb();
        }
        instance.bundle = ResourceBundle.getBundle(DB_CONFIG);
        return instance;
    }

    public String getProperty(String key) {
        String s = null;
        try {
            s = (String) bundle.getObject(key);
        } catch (MissingResourceException exception) {
            logger.error( "ERROR ConfigDBManager getProperty Missing", exception);
        }
        return s;
    }
}
