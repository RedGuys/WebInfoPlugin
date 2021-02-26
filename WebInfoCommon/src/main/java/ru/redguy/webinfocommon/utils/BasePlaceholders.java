package ru.redguy.webinfocommon.utils;

import ru.redguy.webinfocommon.WebStatic;

import java.util.Objects;

public class BasePlaceholders {
    public static void registerBase() {
        PlaceholdersUtils.registerPlaceholder("auth", new Placeholder() {
            @Override
            public String getContent(String[] args, PlaceholdersUtils placeholdersUtils) {
                WebUtils.CookieHandler ch = new WebUtils.CookieHandler(placeholdersUtils.getSession().getHeaders());
                if(!WebStatic.sessions.containsKey(ch.read("session"))) {
                    return "<a href=\"auth/\" class=\"authLink\">" + Language.get("link.auth") + "</a>";
                } else {
                    return "<a href=\"logout/\" class=\"authLink\">" + Language.get("link.logout") + "</a><br>" +
                            "<a href=\"cp\" class=\"authLink\">" + Language.get("link.control_panel") + "</a>";
                }
            }
        });

        PlaceholdersUtils.registerPlaceholder("title", new Placeholder() {
            @Override
            public String getContent(String[] args, PlaceholdersUtils placeholdersUtils) {
                return Language.get("title."+args[0]);
            }
        });

        PlaceholdersUtils.registerPlaceholder("word", new Placeholder() {
            @Override
            public String getContent(String[] args, PlaceholdersUtils placeholdersUtils) {
                return Language.get("word."+args[0]);
            }
        });
    }
}
