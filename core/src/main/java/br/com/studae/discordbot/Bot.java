package br.com.studae.discordbot;

import br.com.studae.commons.Module;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.List;

public class Bot {

    private final JDA jdaInstance;
    private final List<Module> botModules = new ArrayList<>();

    public Bot(JDA jdaInstance) throws InterruptedException {
        this.jdaInstance = jdaInstance;
        jdaInstance.awaitReady();
    }

}
