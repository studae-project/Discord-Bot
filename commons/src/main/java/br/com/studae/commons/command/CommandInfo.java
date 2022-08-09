package br.com.studae.commons.command;

import br.com.studae.commons.command.adapter.Adapter;
import br.com.studae.commons.command.annotation.Command;
import br.com.studae.commons.command.collection.Pair;
import br.com.studae.commons.command.context.Argument;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

public record CommandInfo(
  Command command,
  String name,
  Method method,
  Object object,
  Parameter[] params,
  List<Pair<Argument, Adapter<?>>> parameters
) {
}
