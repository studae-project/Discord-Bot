package br.com.studae.commons.module;

import br.com.studae.commons.Bot;

public abstract class Module {

    private final String name, description;

    public Module(String name) {
        this(name, null);
    }

    public Module(String name, String description) {
        this.name = name;
        this.description = description;

        Bot.getInstance().registerModule(this);
    }

    @Override
    public String toString() {
        return String.format("Module{Name=%s,Description=%s}", name, description == null ? "Not defined" : description);
    }
}
