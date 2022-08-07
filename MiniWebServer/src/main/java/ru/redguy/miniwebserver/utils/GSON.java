package ru.redguy.miniwebserver.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSON {
    public static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
}
