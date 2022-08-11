package br.com.studae.discordbot.command.listener;

import br.com.studae.commons.command.CommandInfo;
import br.com.studae.commons.command.CommandMap;
import br.com.studae.commons.command.CommandProcessor;
import br.com.studae.commons.command.annotation.Command;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public class CommandListener extends ListenerAdapter {

    private CommandMap commandMap;

    private CommandProcessor commandProcessor;

    @Override
    public void onSlashCommandInteraction(
      @NotNull SlashCommandInteractionEvent event
    ) {
        if (event.getUser().isBot()) return;

        CommandInfo commandInfo = commandMap.getCommand(
          event.getName()
        );

        if (commandInfo == null)
            return;

        Command command = commandInfo.command();

        Member member = event.getMember();
        assert member != null;

        if (!member.hasPermission(command.permission()))
            return;

        commandProcessor.process(
          commandInfo.method(),
          commandInfo.object(),
          event
        );
    }
}
