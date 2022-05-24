package ru.redguy.webinfo.common;

import fi.iki.elonen.NanoHTTPD;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface WebPage {
    String url();

    QueryArgument[] args() default {};

    NanoHTTPD.Method method() default NanoHTTPD.Method.GET;
}
