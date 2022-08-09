package br.com.studae.commons.command.adapter;

import br.com.studae.commons.command.annotation.Description;
import br.com.studae.commons.command.annotation.Name;
import br.com.studae.commons.command.context.Argument;
import br.com.studae.commons.command.context.Context;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.lang.reflect.Parameter;

public interface Adapter<T> {

    T adapt(Context context, Argument argument);

    OptionData getData(Argument argument);

    Class<?>[] getTypes();

    default String name(Parameter parameter) {
        return parameter.isAnnotationPresent(Name.class)
          ? parameter.getAnnotation(Name.class).value()
          : parameter.getName();
    }

    default String description(Parameter parameter) {
        return parameter.isAnnotationPresent(Description.class)
          ? parameter.getAnnotation(Description.class).value()
          : "Sem descrição";
    }
}
