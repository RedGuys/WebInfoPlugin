package ru.redguy.webinfo.sponge.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.service.ban.BanService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.ban.Ban;
import org.spongepowered.api.util.ban.BanTypes;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.Player;
import ru.redguy.webinfo.common.controllers.AbstractPlayersController;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.sponge.WebInfoSponge;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SpongePlayersController extends AbstractPlayersController {

    BanService service = Sponge.getServiceManager().provide(BanService.class).orElse(null);

    @Override
    public CompletableFuture<ActionResult> ban(UUID uuid) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (service == null) {
            throw new UnsupportedOperationException();
        }
        service.addBan(Ban.builder().type(BanTypes.PROFILE).profile(GameProfile.of(uuid)).build());
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> ban(UUID uuid, String reason) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (service == null) {
            throw new UnsupportedOperationException();
        }
        service.addBan(Ban.builder().type(BanTypes.PROFILE).reason(Text.of(reason)).profile(GameProfile.of(uuid)).build());
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> banIp(String ip) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (service == null) {
            throw new UnsupportedOperationException();
        }
        Sponge.getScheduler()
                .createTaskBuilder()
                .execute(() -> {
                    try {
                        service.addBan(Ban.builder().type(BanTypes.IP).address(InetAddress.getByName(ip)).build());
                        res.complete(new ActionResult(true));
                    } catch (UnknownHostException e) {
                        res.complete(new ActionResult(false));
                    }
                })
                .submit(WebInfoSponge.instance);
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> banIp(String ip, String reason) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (service == null) {
            throw new UnsupportedOperationException();
        }
        Sponge.getScheduler()
                .createTaskBuilder()
                .execute(() -> {
                    try {
                        service.addBan(Ban.builder().type(BanTypes.IP).reason(Text.of(reason)).address(InetAddress.getByName(ip)).build());
                        res.complete(new ActionResult(true));
                    } catch (UnknownHostException e) {
                        res.complete(new ActionResult(false));
                    }
                })
                .submit(WebInfoSponge.instance);
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> kick(UUID uuid) {
        Optional<org.spongepowered.api.entity.living.player.Player> optPlayer = Sponge.getServer().getPlayer(uuid);
        optPlayer.ifPresent(org.spongepowered.api.entity.living.player.Player::kick);
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> kick(UUID uuid, String reason) {
        Optional<org.spongepowered.api.entity.living.player.Player> optPlayer = Sponge.getServer().getPlayer(uuid);
        optPlayer.ifPresent(p -> p.kick(Text.of(reason)));
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> teleport(UUID uuid, Location location) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (service == null) {
            throw new UnsupportedOperationException();
        }
        Sponge.getScheduler()
                .createTaskBuilder()
                .execute(() -> {
                    Optional<org.spongepowered.api.entity.living.player.Player> optPlayer = Sponge.getServer().getPlayer(uuid);
                    if(optPlayer.isPresent()) {
                        optPlayer.get().setLocation(TransformUtils.transform(location));
                        res.complete(new ActionResult(true));
                    } else {
                        res.complete(new ActionResult(false).setComment("Player not found!"));
                    }
                })
                .submit(WebInfoSponge.instance);
        return res;
    }

    @Override
    public List<Player> getPlayersList() {
        List<Player> result = new ArrayList<>();
        for (org.spongepowered.api.entity.living.player.Player onlinePlayer : Sponge.getServer().getOnlinePlayers()) {
            result.add(TransformUtils.transform(onlinePlayer));
        }
        return result;
    }

    @Override
    public Player getPlayerInfo(UUID uuid) {
        return TransformUtils.transform(Sponge.getServer().getPlayer(uuid).orElse(null));
    }
}
