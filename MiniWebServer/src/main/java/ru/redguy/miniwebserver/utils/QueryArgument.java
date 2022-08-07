package ru.redguy.miniwebserver.utils;

import ru.redguy.miniwebserver.utils.arguments.QueryArgumentType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface QueryArgument {
    String name();

    Class<? extends QueryArgumentType> type();

    boolean required() default true;
}
