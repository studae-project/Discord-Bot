package br.com.studae.commons.command.context;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.Optional;

public record Context(
  Member member,
  Guild guild,
  SlashCommandInteractionEvent event,
  MessageChannel channel
) {

    public Optional<OptionMapping> getOptionMapping(String name) {
        return Optional.ofNullable(event.getOption(name));
    }

    public void reply(String message) {
        event.getHook().sendMessage(message).queue();
    }

    public void reply(MessageEmbed embed, MessageEmbed... embeds) {
        event.getHook().sendMessageEmbeds(embed, embeds).queue();
    }
}
