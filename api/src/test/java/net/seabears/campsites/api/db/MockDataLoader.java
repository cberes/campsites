package net.seabears.campsites.api.db;

import java.util.function.Consumer;

public interface MockDataLoader {
    void load(Consumer<Object> persistFunc);
}
