package br.com.studae.discordbot;

import br.com.studae.commons.Bot;
import br.com.studae.commons.module.Module;
import net.dv8tion.jda.api.JDA;

public class BotImpl extends Bot {

    public BotImpl(JDA jdaInstance) throws InterruptedException {
        super(jdaInstance);
        Bot.instance = this;
    }

}
