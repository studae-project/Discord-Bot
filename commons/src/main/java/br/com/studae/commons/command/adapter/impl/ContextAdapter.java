package br.com.studae.commons.command.adapter.impl;

import br.com.studae.commons.command.adapter.Adapter;
import br.com.studae.commons.command.context.Argument;
import br.com.studae.commons.command.context.Context;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Optional;

public class ContextAdapter implements Adapter<Context> {

    @Override
    public Optional<Context> adapt(Context context, Argument argument) {
        return Optional.of(context);
    }

    @Override
    public OptionData getData(Argument argument) {
        return null;
    }

    @Override
    public Class<?>[] getTypes() {
        return new Class[] { Context.class };
    }
}
