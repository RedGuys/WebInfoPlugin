package ru.redguy.miniwebserver.utils;

import fi.iki.elonen.NanoHTTPD;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class PagesMap {

    private final HashMap<String, Page> pages = new HashMap<>();

    public PagesMap() {}

    public void put(NanoHTTPD.Method method, String path, Page page) {
        pages.put(join(method, path), page);
    }

    public boolean containsKey(NanoHTTPD.Method method, String path) {
        return pages.containsKey(join(method, path));
    }

    public Page get(NanoHTTPD.Method method, String path) {
        return pages.get(join(method, path));
    }

    public Set<Pair<NanoHTTPD.Method, String>> keySet() {
        return pages.keySet().stream().map(this::split).collect(Collectors.toSet());
    }

    @Contract(pure = true)
    private @NotNull String join(NanoHTTPD.@NotNull Method method, String path) {
        return method.name() + ":" + path;
    }

    @Contract("_ -> new")
    private @NotNull Pair<NanoHTTPD.Method, String> split(@NotNull String path) {
        String[] parts = path.split(":");
        return new Pair<>(NanoHTTPD.Method.valueOf(parts[0]), String.join(":", Arrays.copyOfRange(parts, 1, parts.length)));
    }
}
