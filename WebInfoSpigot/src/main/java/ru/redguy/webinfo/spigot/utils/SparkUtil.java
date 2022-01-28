package ru.redguy.webinfo.spigot.utils;

import me.lucko.spark.api.Spark;
import me.lucko.spark.api.SparkProvider;
import me.lucko.spark.api.statistic.StatisticWindow;
import me.lucko.spark.api.statistic.types.DoubleStatistic;

public class SparkUtil {
    public static double getTPS() {
        Spark spark = SparkProvider.get();
        DoubleStatistic<StatisticWindow.TicksPerSecond> tps = spark.tps();
        if(tps == null) {
            throw new UnsupportedOperationException();
        }
        double tpsd = tps.poll(StatisticWindow.TicksPerSecond.MINUTES_1);
        return Math.floor((tpsd>20?20:tpsd) * 100) / 100;
    }
}
