package ru.redguy.webinfocommon.utils;

import java.util.List;

public class InfoUtils {

    private static IInfoUtils infoUtils;

    public static void InjectInfoUtils(IInfoUtils infoUtils) {
        InfoUtils.infoUtils = infoUtils;
    }
    public static IInfoUtils getInstance() {return infoUtils;}
}
