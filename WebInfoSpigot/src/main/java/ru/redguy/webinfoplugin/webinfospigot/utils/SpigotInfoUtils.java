package ru.redguy.webinfoplugin.webinfospigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.redguy.webinfocommon.utils.IInfoUtils;

import java.util.ArrayList;
import java.util.List;

public class SpigotInfoUtils implements IInfoUtils {

    @Override
    public String getMCVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public List<String> getPlayersList() {
        List<String> result = new ArrayList<String>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            result.add(onlinePlayer.getName());
        }
        return result;
    }
}
