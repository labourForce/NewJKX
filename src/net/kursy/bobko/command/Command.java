package net.kursy.bobko.command;


import net.kursy.bobko.holder.Holder;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request);
}
