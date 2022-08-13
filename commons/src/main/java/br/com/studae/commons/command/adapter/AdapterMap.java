package br.com.studae.commons.command.adapter;

import br.com.studae.commons.command.adapter.impl.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class AdapterMap extends HashSet<Adapter<?>> {

    public Adapter<?> getAdapter(Class<?> type) {
        return stream()
          .filter(adapter -> Arrays.asList(adapter.getTypes()).contains(type))
          .findAny()
          .orElse(null);
    }

    public void registerDefaultAdapters() {
        addAll(List.of(
                new StringAdapter(),
                new BooleanAdapter(),
                new ContextAdapter(),
                /*  NUMBER ADAPTERS  */
                new IntegerAdapter(),
                new DoubleAdapter(),
                new LongAdapter(),
                /*  NUMBER ADAPTERS  */
                /*  JDA ADAPTERS  */
                new ChannelAdapter(),
                new AttachmentAdapter(),
                new UserAdapter(),
                new MemberAdapter(),
                new RoleAdapter()
                /*  JDA ADAPTERS  */
        ));
    }
}
