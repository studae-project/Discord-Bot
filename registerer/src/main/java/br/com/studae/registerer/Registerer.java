package br.com.studae.registerer;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class Registerer {

    public static void registerEvent(ListenerAdapter listenerAdapter) {
        EventManager.getInstance().addListener(listenerAdapter);
    }

    public static List<ListenerAdapter> getAllEvents() {
        return EventManager.getInstance().getRegisteredEvents();
    }

}
