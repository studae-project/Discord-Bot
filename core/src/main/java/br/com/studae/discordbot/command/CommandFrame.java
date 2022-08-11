package br.com.studae.discordbot.command;

import br.com.studae.commons.command.CommandInfo;
import br.com.studae.commons.command.CommandMap;
import br.com.studae.commons.command.CommandProcessor;
import br.com.studae.commons.command.adapter.Adapter;
import br.com.studae.commons.command.adapter.AdapterMap;
import br.com.studae.commons.command.annotation.Command;
import br.com.studae.commons.command.context.Argument;
import br.com.studae.commons.command.exception.CommandAlreadyRegisteredException;
import br.com.studae.commons.command.exception.NoSuchAdapterException;
import br.com.studae.discordbot.command.listener.CommandListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CommandFrame {

    private final JDA jda;

    private CommandProcessor commandProcessor;

    private AdapterMap adapterMap;
    private CommandMap commandMap;

    public void initialize() {
        adapterMap = new AdapterMap();
        commandMap = new CommandMap();

        adapterMap.registerDefaultAdapters();

        commandProcessor = new CommandProcessor(commandMap);

        jda.addEventListener(
          new CommandListener(commandMap, commandProcessor)
        );
    }

    public void registerCommand(Object object) {
        Class<?> clazz = object.getClass();

        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
          .filter(method -> method.isAnnotationPresent(Command.class))
          .toList();

        for (Method method : methods) {
            Command command = method.getAnnotation(Command.class);

            if (commandMap.containsKey(command.name()))
                throw new CommandAlreadyRegisteredException(command.name());

            List<CommandCreateAction> actions = provideActions(command);
            Map<Argument, Adapter<?>> arguments = provideArguments(method, actions);

            CommandInfo commandInfo = new CommandInfo(
              command,
              command.name(),
              method,
              object,
              method.getParameters(),
              arguments
            );

            for (CommandCreateAction action : actions) {
                action.queue(cmd -> {
                    commandMap.put(
                      cmd.getName(),
                      commandInfo
                    );
                });
            }
        }
    }

    public void registerCommands(Object... objects) {
        Arrays.stream(objects).forEach(this::registerCommand);
    }

    public void registerPackageCommands(String destination) {
        // TODO: Implement register all commands from a package
    }

    private Map<Argument, Adapter<?>> provideArguments(Method method, List<CommandCreateAction> actions) {
        HashMap<Argument, Adapter<?>> arguments = new HashMap<>();

        Parameter[] parameters = method.getParameters();
        for (int index = 0; index < parameters.length; index++) {
            Parameter parameter = parameters[index];

            Adapter<?> adapter = adapterMap.getAdapter(parameter.getType());

            if (adapter == null) {
                throw new NoSuchAdapterException(parameter.getType());
            }

            Argument argument = new Argument(
              index,
              parameter,
              parameters,
              parameter.getDeclaredAnnotations()
            );

            OptionData data = adapter.getData(argument);

            if (data != null) {
                for (CommandCreateAction action : actions) {
                    if (argument.isRequired())
                        data.setRequired(true);

                    action.addOptions(data);
                }
            };

            arguments.put(argument, adapter);
        }
        return arguments;
    }

    private List<CommandCreateAction> provideActions(Command command) {
        return jda.getGuilds()
          .stream()
          .map(guild -> guild.upsertCommand(command.name(), command.description()))
          .collect(Collectors.toList());
    }

    /*private List<CommandCreateAction> provideActions(Command command) {
        List<CommandCreateAction> actions = new ArrayList<>();

        for (Guild guild : jda.getGuilds()) {
            for (String name : command.names()) {
                actions.add(guild.upsertCommand(
                  name,
                  command.description()
                ));
            }
        }
        rretur
    }*/
}
