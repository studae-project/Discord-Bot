package br.com.studae.commons.command;

import br.com.studae.commons.command.adapter.Adapter;
import br.com.studae.commons.command.collection.Pair;
import br.com.studae.commons.command.context.Argument;
import br.com.studae.commons.command.context.Context;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@AllArgsConstructor
public class CommandProcessor {

    private CommandMap commandMap;

    public void process(
      Method method,
      Object instance,
      SlashCommandInteractionEvent event
    ) {
        try {
            CommandInfo commandInfo = commandMap.getCommand(event.getName());
            Object[] params = new Object[commandInfo.params().length];

            Context context = new Context(
              event.getMember(),
              event.getGuild(),
              event,
              event.getMessageChannel()
            );

            for (Pair<Argument, Adapter<?>> parameter : commandInfo.parameters()) {
                Argument argument = parameter.a();
                Adapter<?> adapter = parameter.b();

                params[argument.getIndex()] = adapter.adapt(context, argument);
            }

            event.deferReply().queue();

            method.invoke(instance, params);
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }
}
