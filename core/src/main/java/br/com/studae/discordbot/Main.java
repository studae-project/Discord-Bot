package br.com.studae.discordbot;

import br.com.studae.commons.module.Module;
import br.com.studae.commons.module.StudaeModule;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    // do a smart cast and get all events registered!
    public static List<Module> applicationModules = new Reflections("br.com.studae")
            .getTypesAnnotatedWith(StudaeModule.class)
            .stream().map(aClass -> {
                try {
                    return aClass.getDeclaredConstructors()[0].newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    return null;
                }
            }).map(o -> (Module) o).collect(Collectors.toList());

    public static void main(String[] args) throws Exception {

        String token = System.getenv("TOKEN");

        for (Module applicationModule : applicationModules) {
            System.out.println(applicationModule);
        }

        System.out.println("Iniciando bot discord...");
        long start = System.currentTimeMillis();

        JDABuilder builder = JDABuilder.createDefault(token)
                .enableIntents(Arrays.stream(GatewayIntent.values()).toList())
                .setBulkDeleteSplittingEnabled(false) // enable the bulk delete event
                .setChunkingFilter(ChunkingFilter.NONE) // disable member chunking on startup
                .setActivity(Activity.playing("Com os membros da Studae")) // set activity (like "playing Something")
                .addEventListeners((EventListener) event -> {
                    if (!(event instanceof ReadyEvent readyEvent)) return;

                    System.out.printf("Bot inicializado em %f segundos\n", (System.currentTimeMillis() - start) / 1000.0);
                    System.out.printf("Atualmente, estou monitorando %d grupos!\n", readyEvent.getGuildTotalCount());

                    TextChannel channel = readyEvent.getJDA().getGuilds().get(0).getTextChannelsByName("geral", true).get(0);
                    channel.sendMessage("Olá pessoal da Studae, sou eu, o Studae Bot!").queue();
                }).addEventListeners((Object[]) applicationModules.stream().flatMap(module -> module.getModuleEvents().stream()).toArray(ListenerAdapter[]::new));

        new Bot(builder.build());
    }

}
