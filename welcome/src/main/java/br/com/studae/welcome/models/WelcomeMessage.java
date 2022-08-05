package br.com.studae.welcome.models;

import lombok.Getter;

@Getter
public class WelcomeMessage {

    private final String message = "Seja bem vindo a nossa comunidade!";

    private WelcomeMessage() {

    }

    private static WelcomeMessage welcomeMessage;

    public static WelcomeMessage getInstance() {
        return welcomeMessage == null ? welcomeMessage = new WelcomeMessage() : welcomeMessage;
    }
}
