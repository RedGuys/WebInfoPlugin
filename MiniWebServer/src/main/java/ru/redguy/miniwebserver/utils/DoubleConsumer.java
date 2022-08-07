package ru.redguy.miniwebserver.utils;

@FunctionalInterface
public interface DoubleConsumer<T, R> {
    void accept(T t, R r);
}
