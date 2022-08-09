package br.com.studae.welcome;

import br.com.studae.commons.module.Module;
import br.com.studae.commons.module.StudaeModule;
import br.com.studae.welcome.listeners.UserJoinServerEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

@StudaeModule
public class Welcome extends Module {

    @Override
    public List<ListenerAdapter> registerModuleEvents() {
        return List.of(new UserJoinServerEvent());
    }
}
