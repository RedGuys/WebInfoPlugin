package ru.redguy.webinfo.spigot.pages.discordsrv;

import fi.iki.elonen.NanoHTTPD;
import github.scarsz.discordsrv.DiscordSRV;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.Request;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebPage(url = "/plugin/discordsrv/")
public class Info implements IWebPage {
    @Override
    public Response getPage(Request req, HashMap<String, ArrayList<Object>> args) throws Exception {
        DiscordSRV d = DiscordSRV.getPlugin();
        return Response.OK(new Resp(
                d.getChannels(),
                d.getMainGuild().getName()
        ));
    }

    class Resp {
        Map<String, String> channels;
        String guildName;

        public Resp(Map<String, String> channels, String guildName) {
            this.channels = channels;
            this.guildName = guildName;
        }
    }
}
