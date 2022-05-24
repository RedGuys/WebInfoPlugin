package ru.redguy.webinfo.spigot.pages.discordsrv.links;

import github.scarsz.discordsrv.DiscordSRV;
import org.jetbrains.annotations.NotNull;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.QueryArgument;
import ru.redguy.webinfo.common.QueryArgumentType;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.Request;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@WebPage(url = "/plugin/discordsrv/links/getplayeruuid", args = {
        @QueryArgument(name = "id", type = QueryArgumentType.STRING)
})
public class GetPlayerUUID implements IWebPage {
    @Override
    public Response getPage(Request req, @NotNull HashMap<String, ArrayList<Object>> args) {
        DiscordSRV d = DiscordSRV.getPlugin();
        UUID id = d.getAccountLinkManager().getUuid((String) args.get("id").get(0));
        return Response.OK(new Resp(
                (String) args.get("id").get(0),
                id
        ));
    }

    static class Resp {
        String discordId;
        UUID player;

        public Resp(String discordId, UUID player) {
            this.discordId = discordId;
            this.player = player;
        }
    }
}
