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
import java.util.Map;
import java.util.UUID;

@WebPage(url = "/plugin/discordsrv/links/")
public class Get implements IWebPage {
    @Override
    public Response getPage(Request req, HashMap<String, ArrayList<Object>> args) {
        DiscordSRV d = DiscordSRV.getPlugin();
        return Response.OK(new Resp(
                d.getAccountLinkManager().getLinkedAccounts(),
                d.getAccountLinkManager().getLinkedAccountCount()
        ));
    }

    static class Resp {
        Map<String, UUID> users;
        int count;

        public Resp(Map<String, UUID> users, int count) {
            this.users = users;
            this.count = count;
        }
    }
}
