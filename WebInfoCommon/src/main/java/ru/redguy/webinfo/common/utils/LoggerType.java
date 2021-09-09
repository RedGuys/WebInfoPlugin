package ru.redguy.webinfo.common.utils;

public enum LoggerType {
    Client("Client"),
    Web("Web");

    private String name;

    LoggerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
