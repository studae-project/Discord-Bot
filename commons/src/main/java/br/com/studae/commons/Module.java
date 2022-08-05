package br.com.studae.commons;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

@StudaeModule
public abstract class Module {

    private final List<ListenerAdapter> events = new ArrayList<>();

    public Module() {

    }

    public abstract List<ListenerAdapter> registerModuleEvents();

    public List<ListenerAdapter> getModuleEvents() {
        return events;
    }
}
