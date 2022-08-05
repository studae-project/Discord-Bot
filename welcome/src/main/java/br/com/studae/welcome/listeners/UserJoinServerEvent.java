package br.com.studae.welcome.listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class UserJoinServerEvent extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Member member = event.getMember();
        if (event.getMessage().getContentRaw().equals("kalabanga")) {
            if (event.getChannel().getName().equals("geral")) {
                assert member != null;
                member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Olá, seja bem vindo a nossa comunidade!").queue());
            }
        }
    }
}
