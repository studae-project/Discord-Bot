package br.com.studae.commons.command.context;

import br.com.studae.commons.command.annotation.Parameter;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

@Getter
public class Argument {

    private int index;
    private java.lang.reflect.Parameter parameter;

    private String name;

    private List<java.lang.reflect.Parameter> parameters;
    private List<Annotation> annotations;

    private boolean required;

    public Argument(
            int index,
            java.lang.reflect.Parameter parameter,
            java.lang.reflect.Parameter[] parameters,
            Annotation[] annotations
    ) {
        this.index = index;
        this.parameter = parameter;
        this.name = parameter.getName();
        this.parameters = Arrays.asList(parameters);
        this.annotations = Arrays.asList(annotations);
        this.required = !parameter.isAnnotationPresent(Parameter.class) || parameter.getAnnotation(Parameter.class).required();
    }

    public boolean hasAnnotation(Class<Annotation> annotation) {
        return parameter.isAnnotationPresent(annotation);
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotation) {
        return parameter.getAnnotation(annotation);
    }
}
