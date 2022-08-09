package br.com.studae.commons.command.exception;

public class CommandAlreadyRegisteredException extends RuntimeException {

    public CommandAlreadyRegisteredException(String name) {
        super(String.format("Command %s already is registered.", name));
    }
}
