package br.com.studae.discordbot;

import net.dv8tion.jda.api.JDA;

public class Bot {

    private final JDA jdaInstance;

    public Bot(JDA jdaInstance) throws InterruptedException {
        this.jdaInstance = jdaInstance;
        jdaInstance.awaitReady();
    }

}
