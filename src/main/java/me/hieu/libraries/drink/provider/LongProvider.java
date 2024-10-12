package me.hieu.libraries.drink.provider;

import me.hieu.libraries.drink.argument.CommandArg;
import me.hieu.libraries.drink.exception.CommandExitMessage;
import me.hieu.libraries.drink.parametric.DrinkProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

public class LongProvider extends DrinkProvider<Long> {

    public static final LongProvider INSTANCE = new LongProvider();

    @Override
    public boolean doesConsumeArgument() {
        return true;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean allowNullArgument() {
        return false;
    }

    @Nullable
    @Override
    public Long defaultNullValue() {
        return 0L;
    }

    @Override
    public Long provide(@Nonnull CommandArg arg, @Nonnull List<? extends Annotation> annotations) throws CommandExitMessage {
        String s = arg.get();
        try {
            return Long.parseLong(s);
        }
        catch (NumberFormatException ex) {
            throw new CommandExitMessage("Required: Long Number, Given: '" + s + "'");
        }
    }

    @Override
    public String argumentDescription() {
        return "long number";
    }

}
