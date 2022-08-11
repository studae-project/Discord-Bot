package br.com.studae.commons.command;

import br.com.studae.commons.command.adapter.Adapter;
import br.com.studae.commons.command.annotation.Command;
import br.com.studae.commons.command.context.Argument;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

public record CommandInfo(
  Command command,
  String name,
  Method method,
  Object object,
  Parameter[] params,
  Map<Argument, Adapter<?>> parameters
) {}