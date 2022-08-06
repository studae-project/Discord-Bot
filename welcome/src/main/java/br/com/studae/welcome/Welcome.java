package br.com.studae.welcome;

import br.com.studae.commons.Module;
import br.com.studae.commons.StudaeModule;
import br.com.studae.welcome.listeners.UserJoinServerEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

@StudaeModule
public class Welcome extends Module {

    @Override
    public List<ListenerAdapter> registerModuleEvents() {
        return List.of(new UserJoinServerEvent());
    }
}
