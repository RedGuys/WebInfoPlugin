package ru.redguy.miniwebserver.utils;

import fi.iki.elonen.NanoHTTPD;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebPage {
    String value();

    QueryArgument[] args() default {};

    NanoHTTPD.Method method() default NanoHTTPD.Method.GET;
}
