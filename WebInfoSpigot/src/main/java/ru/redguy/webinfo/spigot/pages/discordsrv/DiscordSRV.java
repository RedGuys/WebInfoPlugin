package ru.redguy.webinfo.spigot.pages.discordsrv;

import org.jetbrains.annotations.NotNull;
import ru.redguy.miniwebserver.utils.*;
import ru.redguy.miniwebserver.utils.arguments.StringArgument;
import ru.redguy.miniwebserver.utils.arguments.UUIDArgument;

import java.util.Map;
import java.util.UUID;

@Router("/plugin/discordsrv")
public class DiscordSRV {
    @WebPage("/")
    public void index(WebRequest req, @NotNull WebResponse res) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        res.setResponse(new Resp1(
                d.getChannels(),
                d.getMainGuild().getName()
        ));
    }

    @WebPage(value = "/links/generatecode", args = {
            @QueryArgument(name = "uuid", type = UUIDArgument.class)
    })
    public void generateCode(@NotNull WebRequest req, @NotNull WebResponse res) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        String code = d.getAccountLinkManager().generateCode((UUID) req.getArguments().get("uuid").get(0));
        res.setResponse(new Resp2(
                code,
                (UUID) req.getArguments().get("uuid").get(0)
        ));
    }

    @WebPage("/links")
    public void links(WebRequest req, @NotNull WebResponse res) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        res.setResponse(new Resp3(
                d.getAccountLinkManager().getLinkedAccounts(),
                d.getAccountLinkManager().getLinkedAccountCount()
        ));
    }

    @WebPage(value = "/links/getdiscordid", args = {
            @QueryArgument(name = "uuid", type = UUIDArgument.class)
    })
    public void getDiscordId(@NotNull WebRequest req, @NotNull WebResponse res) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        String id = d.getAccountLinkManager().getDiscordId((UUID) req.getArguments().get("uuid").get(0));
        res.setResponse(new Resp4(
                id,
                (UUID) req.getArguments().get("uuid").get(0)
        ));
    }

    @WebPage(value = "/links/getplayeruuid", args = {
            @QueryArgument(name = "id", type = StringArgument.class)
    })
    public void getPlayerUUID(@NotNull WebRequest req, @NotNull WebResponse res) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        UUID id = d.getAccountLinkManager().getUuid((String) req.getArguments().get("id").get(0));
        res.setResponse(new Resp4(
                (String) req.getArguments().get("id").get(0),
                id
        ));
    }

    @WebPage(value = "/links/link", args = {
            @QueryArgument(name = "uuid", type = UUIDArgument.class),
            @QueryArgument(name = "id", type = StringArgument.class)
    })
    public void link(@NotNull WebRequest req, @NotNull WebResponse res) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        d.getAccountLinkManager().link((String) req.getArguments().get("id").get(0), (UUID) req.getArguments().get("uuid").get(0));
        res.setResponse(new Resp4(
                (String) req.getArguments().get("id").get(0),
                (UUID) req.getArguments().get("uuid").get(0)
        ));
    }

    @WebPage(value = "/links/unlink", args = {
            @QueryArgument(name = "uuid", type = UUIDArgument.class)
    })
    public void unlink(@NotNull WebRequest req, @NotNull WebResponse res) {
        github.scarsz.discordsrv.DiscordSRV d = github.scarsz.discordsrv.DiscordSRV.getPlugin();
        String id = d.getAccountLinkManager().getDiscordId((UUID) req.getArguments().get("uuid").get(0));
        d.getAccountLinkManager().unlink((UUID) req.getArguments().get("uuid").get(0));
        res.setResponse(new Resp4(
                id,
                (UUID) req.getArguments().get("uuid").get(0)
        ));
    }

    static class Resp4 {
        String discordId;
        UUID player;

        public Resp4(String discordId, UUID player) {
            this.discordId = discordId;
            this.player = player;
        }
    }

    static class Resp3 {
        Map<String, UUID> users;
        int count;

        public Resp3(Map<String, UUID> users, int count) {
            this.users = users;
            this.count = count;
        }
    }

    static class Resp2 {
        String code;
        UUID player;

        public Resp2(String code, UUID player) {
            this.code = code;
            this.player = player;
        }
    }

    static class Resp1 {
        Map<String, String> channels;
        String guildName;

        public Resp1(Map<String, String> channels, String guildName) {
            this.channels = channels;
            this.guildName = guildName;
        }
    }
}
