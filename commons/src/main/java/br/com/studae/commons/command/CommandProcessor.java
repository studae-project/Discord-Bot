package br.com.studae.commons.command;

import br.com.studae.commons.command.adapter.Adapter;
import br.com.studae.commons.command.context.Argument;
import br.com.studae.commons.command.context.Context;
import br.com.studae.commons.command.exception.NoSuchArgumentException;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

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

            for (Map.Entry<Argument, Adapter<?>> parameter : commandInfo.parameters().entrySet()) {
                Argument argument = parameter.getKey();
                Adapter<?> adapter = parameter.getValue();

                Optional<?> optional = adapter.adapt(context, argument);
                if(optional.isPresent())
                    params[argument.getIndex()] = optional.get();
                else
                    throw new NoSuchArgumentException(argument.getName());
            }

            event.deferReply().queue();

            method.invoke(instance, params);
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }
}
