package me.hieu.libraries.drink;

import me.hieu.libraries.drink.command.DrinkAuthorizer;
import me.hieu.libraries.drink.command.DrinkCommandContainer;
import me.hieu.libraries.drink.modifier.DrinkModifier;
import me.hieu.libraries.drink.parametric.binder.DrinkBinder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;

public interface CommandService {

    DrinkCommandContainer register(@Nonnull Object handler, @Nonnull String name, @Nullable String... aliases);

    DrinkCommandContainer registerSub(@Nonnull DrinkCommandContainer root, @Nonnull Object handler);

    void registerCommands();

    <T> DrinkBinder<T> bind(@Nonnull Class<T> type);

    <T> void registerModifier(@Nonnull Class<? extends Annotation> annotation, @Nonnull Class<T> type, @Nonnull DrinkModifier<T> modifier);

    @Nullable
    DrinkCommandContainer get(@Nonnull String name);

    void setAuthorizer(@Nonnull DrinkAuthorizer authorizer);

}
