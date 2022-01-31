package ru.redguy.webinfo.spigot.pages.discordsrv.links;

import github.scarsz.discordsrv.DiscordSRV;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.QueryArgument;
import ru.redguy.webinfo.common.QueryArgumentType;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.Request;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@WebPage(url = "/plugin/discordsrv/links/generatecode",args = {
        @QueryArgument(name="uuid",type = QueryArgumentType.UUID)
})
public class GenerateCode implements IWebPage {
    @Override
    public Response getPage(Request req, HashMap<String, ArrayList<Object>> args) {
        DiscordSRV d = DiscordSRV.getPlugin();
        String code = d.getAccountLinkManager().generateCode((UUID) args.get("uuid").get(0));
        return Response.OK(new Resp(
                code,
                (UUID) args.get("uuid").get(0)
        ));
    }

    static class Resp {
        String code;
        UUID player;

        public Resp(String code, UUID player) {
            this.code = code;
            this.player = player;
        }
    }
}
