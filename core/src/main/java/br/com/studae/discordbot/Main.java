package br.com.studae.discordbot;

import br.com.studae.discordbot.exceptions.TokenNotProvidedException;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.utils.ChunkingFilter;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) throw new TokenNotProvidedException();

        System.out.println("Iniciando bot discord...");
        long start = System.currentTimeMillis();

        JDABuilder builder = JDABuilder.createDefault(args[0])
                .setBulkDeleteSplittingEnabled(false) // enable the bulk delete event
                .setChunkingFilter(ChunkingFilter.NONE) // disable member chunking on startup
                .setActivity(Activity.playing("Com os membros da Studae")) // set activity (like "playing Something")
                .addEventListeners((EventListener) event -> {
                    if (!(event instanceof ReadyEvent readyEvent)) return;

                    System.out.printf("Bot inicializado em %f segundos\n", (System.currentTimeMillis() - start) / 1000.0);
                    System.out.printf("Atualmente, estou monitorando %d grupos!\n", readyEvent.getGuildTotalCount());
                });

        new Bot(builder.build());
    }

}
