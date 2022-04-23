package ru.redguy.webinfo.spigot.utils;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import ru.redguy.webinfo.common.controllers.AbstractPlayersController;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.spigot.WebInfoSpigot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SpigotPlayersController extends AbstractPlayersController {
    @Override
    public CompletableFuture<ActionResult> ban(UUID uuid) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        Bukkit.getBanList(BanList.Type.NAME).addBan(Bukkit.getOfflinePlayer(uuid).getName(), "Ban from WebInfo", null, null);
        Bukkit.getScheduler().runTask(WebInfoSpigot.getInstance(), () -> {
            if (Bukkit.getPlayer(uuid) != null) Bukkit.getPlayer(uuid).kickPlayer("You has been banned!");
            res.complete(new ActionResult(true));
        });
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> ban(UUID uuid, String reason) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        Bukkit.getBanList(BanList.Type.NAME).addBan(Bukkit.getOfflinePlayer(uuid).getName(), reason, null, null);
        Bukkit.getScheduler().runTask(WebInfoSpigot.getInstance(), () -> {
            if (Bukkit.getPlayer(uuid) != null) Bukkit.getPlayer(uuid).kickPlayer(reason);
            res.complete(new ActionResult(true));
        });
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> banIp(String ip) {
        Bukkit.banIP(ip);
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        Bukkit.getScheduler().runTask(WebInfoSpigot.getInstance(), () -> {
            for (org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
                if (player.getAddress().toString().substring(1).split(":")[0].equals(ip))
                    player.kickPlayer("You has been banned!");
            }
            res.complete(new ActionResult(true));
        });
        return res;
    }

    @Override
    public List<Player> getPlayersList() {
        List<Player> result = new ArrayList<>();
        for (org.bukkit.entity.Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            result.add(TransformUtils.transform(onlinePlayer));
        }
        return result;
    }

    @Override
    public CompletableFuture<ActionResult> banIp(String ip, String reason) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        Bukkit.getBanList(BanList.Type.IP).addBan(ip, reason, null, null);
        Bukkit.getScheduler().runTask(WebInfoSpigot.getInstance(), () -> {
            for (org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
                if (player.getAddress().toString().substring(1).split(":")[0].equals(ip)) player.kickPlayer(reason);
            }
            res.complete(new ActionResult(true));
        });
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> kick(UUID uuid) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        Bukkit.getScheduler().runTask(WebInfoSpigot.getInstance(), () -> {
            Bukkit.getPlayer(uuid).kickPlayer("Kick from WebInfo");
            res.complete(new ActionResult(true));
        });
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> kick(UUID uuid, String reason) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        Bukkit.getScheduler().runTask(WebInfoSpigot.getInstance(), () -> {
            Bukkit.getPlayer(uuid).kickPlayer(reason);
            res.complete(new ActionResult(true));
        });
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> teleport(UUID uuid, Location location) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();

        org.bukkit.entity.Player p = Bukkit.getPlayer(uuid);
        if (p == null) res.complete(new ActionResult(false).setComment("Player not found"));
        else {
            p.teleport(TransformUtils.transform(location));
            res.complete(new ActionResult(true));
        }

        return res;
    }

    @Override
    public Player getPlayerInfo(UUID uuid) {
        org.bukkit.entity.Player p = Bukkit.getPlayer(uuid);
        if (p == null) return null;
        return TransformUtils.transform(p);
    }
}
