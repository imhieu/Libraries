package me.hieu.libraries.drink.argument;

import com.google.common.base.Preconditions;
import me.hieu.libraries.drink.annotation.Flag;
import me.hieu.libraries.drink.command.CommandExecution;
import me.hieu.libraries.drink.command.CommandFlag;
import me.hieu.libraries.drink.command.DrinkCommand;
import me.hieu.libraries.drink.command.DrinkCommandService;
import me.hieu.libraries.drink.exception.CommandArgumentException;
import me.hieu.libraries.drink.exception.CommandExitMessage;
import me.hieu.libraries.drink.parametric.CommandParameter;
import me.hieu.libraries.drink.parametric.DrinkProvider;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {

    private final DrinkCommandService commandService;

    public ArgumentParser(DrinkCommandService commandService) {
        this.commandService = commandService;
    }

    public List<String> combineMultiWordArguments(List<String> args) {
        List<String> argList = new ArrayList<>(args.size());
        for (int i = 0; i < args.size(); i++) {
            String arg = args.get(i);
            if (!arg.isEmpty()) {
                final char c = arg.charAt(0);
                if (c == '"' || c == '\'') {
                    final StringBuilder builder = new StringBuilder();
                    int endIndex;
                    for (endIndex = i; endIndex < args.size(); endIndex++) {
                        final String arg2 = args.get(endIndex);
                        if (arg2.charAt(arg2.length() - 1) == c && arg2.length() > 1) {
                            if (endIndex != i) {
                                builder.append(' ');
                            }
                            builder.append(arg2.substring(endIndex == i ? 1 : 0, arg2.length() - 1));
                            break;
                        } else if (endIndex == i) {
                            builder.append(arg2.substring(1));
                        } else {
                            builder.append(' ').append(arg2);
                        }
                    }
                    if (endIndex < args.size()) {
                        arg = builder.toString();
                        i = endIndex;
                    }
                }
            }
            if (!arg.isEmpty()) {
                argList.add(arg);
            }
        }
        return argList;
    }

    @Nonnull
    public Object[] parseArguments(@Nonnull CommandExecution execution, @Nonnull DrinkCommand command, @Nonnull CommandArgs args) throws CommandExitMessage, CommandArgumentException {

        Preconditions.checkNotNull(command, "DrinkCommand cannot be null");
        Preconditions.checkNotNull(args, "CommandArgs cannot be null");

        Object[] arguments = new Object[command.getMethod().getParameterCount()];

        for (int i = 0; i < command.getParameters().getParameters().length; i++) {

            CommandParameter parameter = command.getParameters().getParameters()[i];
            DrinkProvider<?> provider = command.getProviders()[i];
            String value = null;

            if (parameter.isFlag()) {

                Flag flag = parameter.getFlag();
                CommandFlag commandFlag = args.getFlags().get(flag.value());
                value = commandFlag != null ? commandFlag.getValue() : null;

                if (parameter.isFlag() && value == null
                        && !parameter.getType().isAssignableFrom(Boolean.class)
                        && !parameter.getType().isAssignableFrom(boolean.class)
                        && !provider.allowNullArgument()) {

                    arguments[i] = provider.defaultNullValue();
                    continue;
                }

                Object o = provider.provide(new CommandArg(args.getSender(), value, args), parameter.getAllAnnotations());
                o = commandService.getModifierService().executeModifiers(execution, parameter, o);
                arguments[i] = o;
                continue;
            }

            if (parameter.isOptional() && provider.doesConsumeArgument()) {

                if (args.hasNext()) {
                    value = args.next();
                }

                if (value == null && !provider.allowNullArgument()) {

                    value = parameter.getDefaultOptionalValue();

                    Object o = provider.provide(new CommandArg(args.getSender(), value, args), parameter.getAllAnnotations());
                    o = commandService.getModifierService().executeModifiers(execution, parameter, o);
                    arguments[i] = o;
                    continue;
                }

                try {

                    Object o = provider.provide(new CommandArg(args.getSender(), value, args), parameter.getAllAnnotations());
                    o = commandService.getModifierService().executeModifiers(execution, parameter, o);
                    arguments[i] = o;

                } catch (CommandExitMessage ex) {

                    value = parameter.getDefaultOptionalValue();

                    Object o = provider.provide(new CommandArg(args.getSender(), value, args), parameter.getAllAnnotations());
                    o = commandService.getModifierService().executeModifiers(execution, parameter, o);
                    arguments[i] = o;

                    args.skip();
                }

                continue;
            }

            if (provider.doesConsumeArgument()) {

                if (!args.hasNext()) {
                    throw new CommandArgumentException("Missing argument for: " + provider.argumentDescription());
                }

                value = args.next();

                if (value == null && !provider.allowNullArgument()) {
                    throw new CommandArgumentException("Argument already consumed for next argument: " + provider.argumentDescription() + " (this is a provider error!)");
                }
            }

            Object o = provider.provide(new CommandArg(args.getSender(), value, args), parameter.getAllAnnotations());
            o = commandService.getModifierService().executeModifiers(execution, parameter, o);
            arguments[i] = o;
        }
        return arguments;
    }

}
