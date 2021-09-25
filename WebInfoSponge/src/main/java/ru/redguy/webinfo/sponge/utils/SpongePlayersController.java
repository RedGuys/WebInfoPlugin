package ru.redguy.webinfo.sponge.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.service.ban.BanService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.ban.Ban;
import org.spongepowered.api.util.ban.BanTypes;
import ru.redguy.webinfo.common.utils.AbstractPlayersController;
import ru.redguy.webinfo.common.utils.ActionResult;
import ru.redguy.webinfo.sponge.WebInfoSponge;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SpongePlayersController extends AbstractPlayersController {

    BanService service = Sponge.getServiceManager().provide(BanService.class).orElse(null);

    @Override
    public CompletableFuture<ActionResult> ban(UUID uuid) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (service == null) {
            res.complete(new ActionResult(false, true));
            return res;
        }
        service.addBan(Ban.builder().type(BanTypes.PROFILE).profile(GameProfile.of(uuid)).build());
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> ban(UUID uuid, String reason) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (service == null) {
            res.complete(new ActionResult(false, true));
            return res;
        }
        service.addBan(Ban.builder().type(BanTypes.PROFILE).reason(Text.of(reason)).profile(GameProfile.of(uuid)).build());
        res.complete(new ActionResult(true));
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> banIp(String ip) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (service == null) {
            res.complete(new ActionResult(false, true));
            return res;
        }
        Sponge.getScheduler()
                .createTaskBuilder()
                .execute(() -> {
                    try {
                        service.addBan(Ban.builder().type(BanTypes.IP).address(InetAddress.getByName(ip)).build());
                        res.complete(new ActionResult(true));
                    } catch (UnknownHostException e) {
                        res.complete(new ActionResult(false, false));
                    }
                })
                .submit(WebInfoSponge.instance);
        return res;
    }

    @Override
    public CompletableFuture<ActionResult> banIp(String ip, String reason) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        if (service == null) {
            res.complete(new ActionResult(false, true));
            return res;
        }
        Sponge.getScheduler()
                .createTaskBuilder()
                .execute(() -> {
                    try {
                        service.addBan(Ban.builder().type(BanTypes.IP).reason(Text.of(reason)).address(InetAddress.getByName(ip)).build());
                        res.complete(new ActionResult(true));
                    } catch (UnknownHostException e) {
                        res.complete(new ActionResult(false, false));
                    }
                })
                .submit(WebInfoSponge.instance);
        return res;
    }
}
