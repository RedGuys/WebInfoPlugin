package ru.redguy.miniwebserver.utils;

import java.lang.reflect.Method;

public class Page {
    private Object holder;
    private Method method;
    private WebPage annotation;

    public Page(Object holder, Method method, WebPage annotation) {
        this.holder = holder;
        this.method = method;
        this.annotation = annotation;
    }

    public Object getHolder() {
        return holder;
    }

    public Method getMethod() {
        return method;
    }

    public WebPage getAnnotation() {
        return annotation;
    }
}
