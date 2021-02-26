package ru.redguy.webinfosponge.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import ru.redguy.webinfocommon.utils.IInfoUtils;

import java.util.ArrayList;
import java.util.List;

public class SpongeInfoUtils implements IInfoUtils {

    @Override
    public String getMCVersion() {
        return Sponge.getPlatform().getMinecraftVersion().getName();
    }

    @Override
    public List<String> getPlayersList() {
        List<String> result = new ArrayList<String>();
        for (Player onlinePlayer : Sponge.getServer().getOnlinePlayers()) {
            result.add(onlinePlayer.getName());
        }
        return result;
    }
}
