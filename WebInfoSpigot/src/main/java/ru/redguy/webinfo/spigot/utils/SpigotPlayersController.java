package ru.redguy.webinfo.spigot.utils;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.common.utils.AbstractPlayersController;
import ru.redguy.webinfo.common.utils.ActionResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SpigotPlayersController extends AbstractPlayersController {
    @Override
    public CompletableFuture<ActionResult> ban(UUID uuid) {
        Bukkit.getBanList(BanList.Type.NAME).addBan(Bukkit.getOfflinePlayer(uuid).getName(), "Ban from WebInfo", null, null);
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> ban(UUID uuid, String reason) {
        Bukkit.getBanList(BanList.Type.NAME).addBan(Bukkit.getOfflinePlayer(uuid).getName(), reason, null, null);
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> banIp(String ip) {
        Bukkit.banIP(ip);
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> banIp(String ip, String reason) {
        Bukkit.getBanList(BanList.Type.IP).addBan(ip, reason, null, null);
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> teleport(UUID uuid, Location location) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();

        org.bukkit.entity.Player p = Bukkit.getPlayer(uuid);
        if(p == null) res.complete(new ActionResult(false).setComment("Player not found"));
        else {
            p.teleport(TransformUtils.transform(location));
            res.complete(new ActionResult(true));
        }

        return res;
    }

    @Override
    public Player getPlayerInfo(UUID uuid) {
        org.bukkit.entity.Player p = Bukkit.getPlayer(uuid);
        if(p == null) return null;
        return TransformUtils.transform(p);
    }
}
