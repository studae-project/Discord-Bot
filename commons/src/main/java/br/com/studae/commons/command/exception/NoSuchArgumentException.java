package br.com.studae.commons.command.exception;

public class NoSuchArgumentException extends RuntimeException {

    public NoSuchArgumentException(String argument) {
        super(String.format("Argument %s not found on context", argument));
    }
}
