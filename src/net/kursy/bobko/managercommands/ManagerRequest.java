package net.kursy.bobko.managercommands;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ManagerRequest {

    private ResourceBundle bundle;
    private static ManagerRequest instance;
    private static final String SQL_REQUEST = "net.kursy.bobko.managercommands.properties.CommandSQL";

    public static ManagerRequest getInstance() {
        if (instance == null) {
            instance = new ManagerRequest();
        }
        instance.bundle = ResourceBundle.getBundle(SQL_REQUEST);
        return instance;
    }

    public String getSqlCommand(String key) {
        String s = null;
        try {
            s = (String) bundle.getObject(key);
        } catch (MissingResourceException e) {
            System.out.println(e);
        }
        return s;
    }
}

