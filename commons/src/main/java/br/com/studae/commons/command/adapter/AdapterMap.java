package br.com.studae.commons.command.adapter;

import br.com.studae.commons.command.adapter.impl.BooleanAdapter;
import br.com.studae.commons.command.adapter.impl.ContextAdapter;
import br.com.studae.commons.command.adapter.impl.StringAdapter;

import java.util.Arrays;
import java.util.HashSet;

public class AdapterMap extends HashSet<Adapter<?>> {

    public Adapter getAdapter(Class<?> type) {
        return stream()
          .filter(adapter -> Arrays.asList(adapter.getTypes()).contains(type))
          .findAny()
          .orElse(null);
    }

    public void registerDefaultAdapters() {
        add(new StringAdapter());
        add(new BooleanAdapter());
        add(new ContextAdapter());
    }
}
