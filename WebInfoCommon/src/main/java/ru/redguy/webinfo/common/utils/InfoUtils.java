package ru.redguy.webinfo.common.utils;

public class InfoUtils {

    private static IInfoUtils infoUtils;

    public static void InjectInfoUtils(IInfoUtils infoUtils) {
        InfoUtils.infoUtils = infoUtils;
    }
    public static IInfoUtils getInstance() {return infoUtils;}
}
