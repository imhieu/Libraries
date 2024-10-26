package me.hieu.libraries.drink.modifier;

import me.hieu.libraries.drink.command.CommandExecution;
import me.hieu.libraries.drink.exception.CommandExitMessage;
import me.hieu.libraries.drink.parametric.CommandParameter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public interface DrinkModifier<T> {

    Optional<T> modify(@Nonnull CommandExecution execution, @Nonnull CommandParameter commandParameter, @Nullable T argument) throws CommandExitMessage;

}
