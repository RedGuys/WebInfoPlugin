package ru.redguy.webinfocommon.utils;

import java.util.List;

public class InfoUtils {

    private static IInfoUtils infoUtils;

    public static void InjectInfoUtils(IInfoUtils infoUtils) {
        InfoUtils.infoUtils = infoUtils;
    }

    public static String getMCVersion() {
        return infoUtils.getMCVersion();
    }
    public static List<String> getPlayersList() {
        return infoUtils.getPlayersList();
    }
}
