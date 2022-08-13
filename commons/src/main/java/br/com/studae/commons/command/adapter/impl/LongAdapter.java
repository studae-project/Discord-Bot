package br.com.studae.commons.command.adapter.impl;

import br.com.studae.commons.command.adapter.Adapter;
import br.com.studae.commons.command.context.Argument;
import br.com.studae.commons.command.context.Context;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Optional;

public class LongAdapter implements Adapter<Long> {

    @Override
    public Optional<Long> adapt(Context context, Argument argument) {
        return context.getOptionMapping(argument.getName()).map(OptionMapping::getAsLong);
    }

    @Override
    public OptionData getData(Argument argument) {
        return new OptionData(
          OptionType.INTEGER,
          name(argument.getParameter()),
          description(argument.getParameter()),
          argument.isRequired()
        );
    }

    @Override
    public Class<?>[] getTypes() {
        return new Class[] { Long.class, long.class };
    }
}
