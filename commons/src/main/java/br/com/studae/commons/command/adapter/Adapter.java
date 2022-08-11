package br.com.studae.commons.command.adapter;

import br.com.studae.commons.command.annotation.Parameter;
import br.com.studae.commons.command.context.Argument;
import br.com.studae.commons.command.context.Context;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Optional;

public interface Adapter<T> {

    Optional<T> adapt(Context context, Argument argument);

    OptionData getData(Argument argument);

    Class<?>[] getTypes();

    default String name(java.lang.reflect.Parameter parameter) {
        return parameter.isAnnotationPresent(Parameter.class)
          ? parameter.getAnnotation(Parameter.class).name()
          : parameter.getName();
    }

    default String description(java.lang.reflect.Parameter parameter) {
        return parameter.isAnnotationPresent(Parameter.class)
          ? parameter.getAnnotation(Parameter.class).description()
          : "Sem descrição";
    }
}
