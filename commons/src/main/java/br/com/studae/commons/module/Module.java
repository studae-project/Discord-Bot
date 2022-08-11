package br.com.studae.commons.module;

import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {

    public Module() {

    }

    public abstract List<ListenerAdapter> registerModuleEvents();

    public List<ListenerAdapter> getModuleEvents() {
        return registerModuleEvents();
    }

    @Override
    public String toString() {
        return "Module{" +
                "events=" + getModuleEvents() +
                '}';
    }
}
