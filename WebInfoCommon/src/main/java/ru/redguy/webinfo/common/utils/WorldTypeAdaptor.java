package ru.redguy.webinfo.common.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ru.redguy.webinfo.common.structures.World;

import java.io.IOException;

public class WorldTypeAdaptor extends TypeAdapter<World> {
    @Override
    public void write(JsonWriter jsonWriter, World world) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("name").value(world.getName());
        jsonWriter.name("uuid").value(world.getUuid().toString());
        jsonWriter.name("entities").beginArray();
        for (int i = 0; i < world.getEntities().length; i++) {
            jsonWriter.value(world.getEntities()[i].toString());
        }
    }

    @Override
    public World read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
