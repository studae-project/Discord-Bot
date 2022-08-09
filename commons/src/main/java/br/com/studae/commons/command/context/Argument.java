package br.com.studae.commons.command.context;

import br.com.studae.commons.command.annotation.Optional;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

@Getter
public class Argument {

    private int index;
    private Parameter parameter;

    private String name;

    private List<Parameter> parameters;
    private List<Annotation> annotations;

    private boolean required;

    public Argument(
      int index,
      Parameter parameter,
      Parameter[] parameters,
      Annotation[] annotations
    ) {
        this.index = index;
        this.parameter = parameter;
        this.name = parameter.getName();
        this.parameters = Arrays.asList(parameters);
        this.annotations = Arrays.asList(annotations);
        this.required = !parameter.isAnnotationPresent(Optional.class);
    }

    public boolean hasAnnotation(Class<Annotation> annotation) {
        return parameter.isAnnotationPresent(annotation);
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotation) {
        return parameter.getAnnotation(annotation);
    }
}
