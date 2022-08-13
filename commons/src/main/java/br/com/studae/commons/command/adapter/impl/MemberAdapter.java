package br.com.studae.commons.command.adapter.impl;

import br.com.studae.commons.command.adapter.Adapter;
import br.com.studae.commons.command.context.Argument;
import br.com.studae.commons.command.context.Context;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Optional;

public class MemberAdapter implements Adapter<Member> {

    @Override
    public Optional<Member> adapt(Context context, Argument argument) {
        return context.getOptionMapping(argument.getName()).map(OptionMapping::getAsMember);
    }

    @Override
    public OptionData getData(Argument argument) {
        return new OptionData(
          OptionType.USER,
          name(argument.getParameter()),
          description(argument.getParameter()),
          argument.isRequired()
        );
    }

    @Override
    public Class<?>[] getTypes() {
        return new Class[] { Member.class };
    }
}
