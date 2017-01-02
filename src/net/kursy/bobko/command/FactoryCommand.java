package net.kursy.bobko.command;


import net.kursy.bobko.services.*;

import java.util.HashMap;
import java.util.Map;

public class FactoryCommand {

    private final Map<String, Command> map = new HashMap<>();
    private static final FactoryCommand FACTORY = new FactoryCommand();

    private FactoryCommand() {
        map.put("LOGIN", new LoginCommand(new LogIn()));
        map.put("REGISTRATION", new RegistrationCommand(new Registration()));
        map.put("LOGOUT", new LogOutCommand(new LogOut()));
        map.put("WORKFORM", new WorkFormCommand(new WorkForm()));
        map.put("USERACCOUNT", new AccountCommand(new UserAccount()));
        map.put("STATUSCHANGE", new ConfirmCommand(new StatusChange()));

    }

    public static FactoryCommand getInstance() {
        return FACTORY;
    }

    public Command getCommand(String key) {
        return map.get(key.toUpperCase());
    }

}
