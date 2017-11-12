package net.seabears.campsites.api.db.mock;

import net.seabears.campsites.api.db.MockDataLoader;
import org.springframework.core.Ordered;
import org.springframework.data.repository.CrudRepository;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

abstract class AbstractMockDataLoader<T> implements MockDataLoader, Ordered {
    private final Iterable<T> initialData;

    protected AbstractMockDataLoader() {
        this(emptyList());
    }

    protected AbstractMockDataLoader(final Iterable<T> initialData) {
        this.initialData = initialData;
    }

    @Override
    public void load(final Consumer<Object> persistFunc) {
        initialData.forEach(persistFunc);
    }
}
