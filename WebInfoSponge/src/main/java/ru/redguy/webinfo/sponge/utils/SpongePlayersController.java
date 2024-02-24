package ru.redguy.webinfo.sponge.utils;

import net.kyori.adventure.text.Component;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.service.ban.Ban;
import org.spongepowered.api.service.ban.BanService;
import org.spongepowered.api.service.ban.BanTypes;
import ru.redguy.webinfo.common.controllers.AbstractPlayersController;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.sponge.WebInfoSponge;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SpongePlayersController extends AbstractPlayersController {

    BanService banService = Sponge.serviceProvider().provide(BanService.class).orElse(null);

    @Override
    public CompletableFuture<ActionResult> ban(UUID uuid) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (banService == null) {
            throw new UnsupportedOperationException();
        }
        banService.add(Ban.builder().type(BanTypes.PROFILE).profile(GameProfile.of(uuid)).build());
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> ban(UUID uuid, String reason) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (banService == null) {
            throw new UnsupportedOperationException();
        }
        banService.add(Ban.builder().type(BanTypes.PROFILE).reason(Component.text(reason)).profile(GameProfile.of(uuid)).build());
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> banIp(String ip) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (banService == null) {
            throw new UnsupportedOperationException();
        }
        Sponge.asyncScheduler()
                .submit(Task.builder().execute(() -> {
                    try {
                        banService.add(Ban.builder().type(BanTypes.IP).address(InetAddress.getByName(ip)).build());
                        res.complete(new ActionResult(true));
                    } catch (UnknownHostException e) {
                        res.complete(new ActionResult(false));
                    }
                }).build());
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> banIp(String ip, String reason) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (banService == null) {
            throw new UnsupportedOperationException();
        }
        Sponge.asyncScheduler()
                .submit(Task.builder()
                        .execute(() -> {
                            try {
                                banService.add(Ban.builder().type(BanTypes.IP).reason(Component.text(reason)).address(InetAddress.getByName(ip)).build());
                                res.complete(new ActionResult(true));
                            } catch (UnknownHostException e) {
                                res.complete(new ActionResult(false));
                            }
                        }).build());
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> kick(UUID uuid) {
        Optional<ServerPlayer> optPlayer = Sponge.server().player(uuid);
        optPlayer.ifPresent(ServerPlayer::kick);
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> kick(UUID uuid, String reason) {
        Optional<ServerPlayer> optPlayer = Sponge.server().player(uuid);
        optPlayer.ifPresent(p -> p.kick(Component.text(reason)));
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> teleport(UUID uuid, Location location) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        Sponge.asyncScheduler()
                .submit(Task.builder()
                .execute(() -> {
                    Optional<ServerPlayer> optPlayer = Sponge.server().player(uuid);
                    if (optPlayer.isPresent()) {
                        optPlayer.get().setLocation(TransformUtils.transform(location));
                        res.complete(new ActionResult(true));
                    } else {
                        res.complete(new ActionResult(false).setComment("Player not found!"));
                    }
                }).build());
        return res;
    }

    @Override
    public List<Player> getPlayersList() {
        List<Player> result = new ArrayList<>();
        for (org.spongepowered.api.entity.living.player.Player onlinePlayer : Sponge.server().onlinePlayers()) {
            result.add(TransformUtils.transform(onlinePlayer));
        }
        return result;
    }

    @Override
    public Player getPlayerInfo(UUID uuid) {
        return TransformUtils.transform(Sponge.server().player(uuid).orElse(null));
    }
}
