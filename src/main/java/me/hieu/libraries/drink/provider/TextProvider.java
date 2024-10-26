package me.hieu.libraries.drink.provider;

import me.hieu.libraries.drink.argument.CommandArg;
import me.hieu.libraries.drink.exception.CommandExitMessage;
import me.hieu.libraries.drink.parametric.DrinkProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.List;

public class TextProvider extends DrinkProvider<String> {

    public static final TextProvider INSTANCE = new TextProvider();

    @Override
    public boolean doesConsumeArgument() {
        return true;
    }

    @Override
    public boolean allowNullArgument() {
        return false;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Nullable
    @Override
    public String provide(@Nonnull CommandArg arg, @Nonnull List<? extends Annotation> annotations) throws CommandExitMessage {
        StringBuilder builder = new StringBuilder(arg.get());
        while (arg.getArgs().hasNext()) {
            builder.append(" ").append(arg.getArgs().next());
        }
        return builder.toString();
    }

    @Override
    public String argumentDescription() {
        return "text";
    }

}
