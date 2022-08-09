package br.com.studae.commons.command.adapter.impl;

import br.com.studae.commons.command.adapter.Adapter;
import br.com.studae.commons.command.context.Argument;
import br.com.studae.commons.command.context.Context;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class StringAdapter implements Adapter<String> {

    @Override
    public String adapt(Context context, Argument argument) {
        return context.getOptionMapping(name(argument.getParameter()))
          .map(OptionMapping::getAsString)
          .orElse(null);
    }

    @Override
    public OptionData getData(Argument argument) {
        return new OptionData(
          OptionType.STRING,
          name(argument.getParameter()),
          description(argument.getParameter()),
          argument.isRequired()
        );
    }

    @Override
    public Class<?>[] getTypes() {
        return new Class[] { String.class };
    }
}
