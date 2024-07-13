package ru.redguy.webinfo.spigot.pages.discordsrv;

import ru.redguy.jrweb.Context;
import ru.redguy.jrweb.annotations.Page;
import ru.redguy.jrweb.annotations.Param;
import ru.redguy.jrweb.annotations.Router;

import java.util.Map;
import java.util.UUID;

@Router("/plugin/discordsrv")
public class DiscordSRV {
    @Page("/")
    public Resp1 index(Context ctx) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        return new Resp1(
                d.getChannels(),
                d.getMainGuild().getName()
        );
    }

    @Page(value = "/links/generatecode")
    public Resp2 generateCode(Context ctx, @Param("uuid") UUID uuid) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        String code = d.getAccountLinkManager().generateCode(uuid);
        return new Resp2(
                code,
                uuid
        );
    }

    @Page("/links")
    public Resp3 links(Context ctx) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        return new Resp3(
                d.getAccountLinkManager().getLinkedAccounts(),
                d.getAccountLinkManager().getLinkedAccountCount()
        );
    }

    @Page(value = "/links/getdiscordid")
    public Resp4 getDiscordId(Context ctx, @Param("uuid") UUID uuid) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        String id = d.getAccountLinkManager().getDiscordId(uuid);
        return new Resp4(
                id,
                uuid
        );
    }

    @Page(value = "/links/getplayeruuid")
    public Resp4 getPlayerUUID(Context ctx, @Param("id") String id) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        UUID uuid = d.getAccountLinkManager().getUuid(id);
        return new Resp4(
                id,
                uuid
        );
    }

    @Page(value = "/links/link")
    public Resp4 link(Context ctx, @Param("id") String id, @Param("uuid") UUID uuid) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        d.getAccountLinkManager().link(id, uuid);
        return new Resp4(
                id,
                uuid
        );
    }

    @Page(value = "/links/unlink")
    public Resp4 unlink(Context ctx, @Param("uuid") UUID uuid) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        String id = d.getAccountLinkManager().getDiscordId(uuid);
        d.getAccountLinkManager().unlink(uuid);
        return new Resp4(
                id,
                uuid
        );
    }

    public static class Resp4 {
        String discordId;
        UUID player;

        public Resp4(String discordId, UUID player) {
            this.discordId = discordId;
            this.player = player;
        }
    }

    public static class Resp3 {
        Map<String, UUID> users;
        int count;

        public Resp3(Map<String, UUID> users, int count) {
            this.users = users;
            this.count = count;
        }
    }

    public static class Resp2 {
        String code;
        UUID player;

        public Resp2(String code, UUID player) {
            this.code = code;
            this.player = player;
        }
    }

    public static class Resp1 {
        Map<String, String> channels;
        String guildName;

        public Resp1(Map<String, String> channels, String guildName) {
            this.channels = channels;
            this.guildName = guildName;
        }
    }
}
