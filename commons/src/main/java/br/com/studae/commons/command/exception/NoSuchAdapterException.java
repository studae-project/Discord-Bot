package br.com.studae.commons.command.exception;

public class NoSuchAdapterException extends RuntimeException {

    public NoSuchAdapterException(Class<?> classType) {
        super(String.format("No Adapter found for class type %s.", classType.getTypeName()));
    }
}
