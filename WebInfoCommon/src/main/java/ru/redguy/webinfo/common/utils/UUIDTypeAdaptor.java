package ru.redguy.webinfo.common.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.UUID;

public class UUIDTypeAdaptor extends TypeAdapter<UUID> {

    @Override
    public void write(JsonWriter jsonWriter, UUID uuid) throws IOException {
        jsonWriter.value(uuid.toString());
    }

    @Override
    public UUID read(JsonReader jsonReader) throws IOException {
        return UUID.fromString(jsonReader.nextString());
    }
}
