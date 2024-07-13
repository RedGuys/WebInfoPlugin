package ru.redguy.webinfo.common.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ru.redguy.webinfo.common.structures.Location;

import java.io.IOException;

public class LocationTypeAdaptor extends TypeAdapter<Location> {
    @Override
    public void write(JsonWriter jsonWriter, Location location) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("x").value(location.getX());
        jsonWriter.name("y").value(location.getY());
        jsonWriter.name("z").value(location.getZ());
        if(location.getWorld() != null) {
            jsonWriter.name("world").value(location.getWorld());
        }
        jsonWriter.endObject();
    }

    @Override
    public Location read(JsonReader jsonReader) throws IOException {
        double x = 0;
        double y = 0;
        double z = 0;
        String world = null;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "x":
                    x = jsonReader.nextDouble();
                    break;
                case "y":
                    y = jsonReader.nextDouble();
                    break;
                case "z":
                    z = jsonReader.nextDouble();
                    break;
                case "world":
                    world = jsonReader.nextString();
                    break;
            }
        }
        jsonReader.endObject();
        return new Location(x, y, z, world);
    }
}
