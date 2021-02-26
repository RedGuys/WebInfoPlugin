package ru.redguy.webinfocommon.utils;

import fi.iki.elonen.NanoHTTPD;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholdersUtils {

    private static final HashMap<String, Placeholder> placeholders = new HashMap<>();

    public static void registerPlaceholder(String name, Placeholder placeholder) {
        placeholders.put(name, placeholder);
    }

    Pattern pattern = Pattern.compile("\\{(\\w.+ *\\w*)}");

    private final NanoHTTPD.IHTTPSession session;

    public PlaceholdersUtils(NanoHTTPD.IHTTPSession session) {
        this.session = session;
    }

    public NanoHTTPD.IHTTPSession getSession() {
        return session;
    }

    public String work(String page) {
        Matcher match = pattern.matcher(page);
        while (match.find()) {
            for (int i = 1; i <= match.groupCount(); i++) {
                String found = match.group(i);
                if (!found.equals("")) {
                    String placeholder = found.split(" ")[0];
                    if (placeholders.containsKey(placeholder)) {
                        String[] args = found.split(" ");
                        if (args.length == 1) {
                            args = new String[0];
                        } else {
                            args = Arrays.copyOfRange(args, 1, args.length);
                        }
                        page = page.replace("{" + found + "}", placeholders.get(placeholder).getContent(args, this));
                    }
                }
            }
        }
        return page;
    }
}
