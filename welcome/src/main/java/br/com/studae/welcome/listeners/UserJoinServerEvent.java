package br.com.studae.welcome.listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.net.URISyntaxException;

public class UserJoinServerEvent extends ListenerAdapter {

    private static File file = null;

    static {
        try {
            file = new File(UserJoinServerEvent.class.getResource("/kawabanga.png").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.isFromGuild()) {
            Member member = event.getMember();
            if (event.getChannel().getName().equals("geral")) {
                if (event.getMessage().getContentRaw().equals("kawabanga")) {
                    assert member != null;
                    member.getUser().openPrivateChannel().queue(privateChannel -> {
                        privateChannel.sendMessage("Olá, seja bem vindo a nossa comunidade!").queue();
                        privateChannel.sendFile(file).queue();
                    });
                }
            }
        }
    }
}
