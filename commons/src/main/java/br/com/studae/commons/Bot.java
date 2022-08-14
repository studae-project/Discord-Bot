package br.com.studae.commons;

import br.com.studae.commons.module.Module;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.List;

public abstract class Bot {

    protected static Bot instance;

    protected final JDA jdaInstance;
    protected final List<Module> botModules = new ArrayList<>();

    public Bot(JDA jdaInstance) throws InterruptedException {
        if (instance != null) throw new RuntimeException("Bot já iniciado!");

        this.jdaInstance = jdaInstance;
        jdaInstance.awaitReady();
    }

    public void registerModule(Module module) {
        botModules.add(module);
    }

    public static Bot getInstance() {
        return instance;
    }

}
