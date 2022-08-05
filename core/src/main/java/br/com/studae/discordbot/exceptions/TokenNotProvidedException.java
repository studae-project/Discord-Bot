package br.com.studae.discordbot.exceptions;

public class TokenNotProvidedException extends RuntimeException {

    public TokenNotProvidedException() {
        super("Você precisa fornecer seu token como o primeiro argumento!");
    }
}
