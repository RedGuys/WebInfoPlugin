package ru.redguy.webinfo.common.utils;

import java.util.List;

public class ParamsUtils {
    public static boolean parseBoolean(List<String> list, boolean def) {
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
