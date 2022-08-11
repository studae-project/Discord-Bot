package br.com.studae.commons.command;

import java.util.HashMap;

public class CommandMap extends HashMap<String, CommandInfo> {

    public CommandInfo getCommand(String commandName) {
        return get(commandName.toLowerCase());
    }
}
