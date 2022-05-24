package ru.redguy.webinfo.common.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ParamsUtils {
    @Contract(pure = true)
    public static boolean parseBoolean(@NotNull List<String> list, boolean def) {
        boolean result = def;
        for (String s : list) {
            switch (s.toLowerCase()) {
                case "false":
                    result = false;
                    break;
                case "true":
                    result = true;
                    break;
            }
        }
        return result;
    }
}
