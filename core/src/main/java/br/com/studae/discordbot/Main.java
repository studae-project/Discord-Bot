package br.com.studae.discordbot;

import br.com.studae.commons.StudaeModule;
import br.com.studae.registerer.Registerer;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import org.reflections.Reflections;

import java.util.Set;

public class Main {

    // do a smart cast and get all events registered!
    public static Set<Class<?>> applicationModules = new Reflections("br.com.studae").getTypesAnnotatedWith(StudaeModule.class);

    public static void main(String[] args) throws Exception {

        String token = System.getenv("TOKEN");

        System.out.println("Iniciando bot discord...");
        long start = System.currentTimeMillis();

        JDABuilder builder = JDABuilder.createDefault(token)
                .setBulkDeleteSplittingEnabled(false) // enable the bulk delete event
                .setChunkingFilter(ChunkingFilter.NONE) // disable member chunking on startup
                .setActivity(Activity.playing("Com os membros da Studae")) // set activity (like "playing Something")
                .addEventListeners((EventListener) event -> {
                    if (!(event instanceof ReadyEvent readyEvent)) return;

                    System.out.printf("Bot inicializado em %f segundos\n", (System.currentTimeMillis() - start) / 1000.0);
                    System.out.printf("Atualmente, estou monitorando %d grupos!\n", readyEvent.getGuildTotalCount());

                    TextChannel channel = readyEvent.getJDA().getGuilds().get(0).getTextChannelsByName("geral", true).get(0);
                    channel.sendMessage("Olá pessoal da Studae, sou eu, o Studae Bot!").queue();
                })
                .addEventListeners(Registerer.getAllEvents());

        new Bot(builder.build());
    }

}
