package br.com.studae.registerer;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private EventManager() {

    }

    private final List<ListenerAdapter> registeredEvents = new ArrayList<>();

    public void addListener(ListenerAdapter listenerAdapter) {
        this.registeredEvents.add(listenerAdapter);
    }

    public List<ListenerAdapter> getRegisteredEvents() {
        return registeredEvents;
    }

    private static EventManager eventManager;

    public static EventManager getInstance() {
        return eventManager == null ? eventManager = new EventManager() : eventManager;
    }

}
