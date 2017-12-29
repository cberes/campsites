package net.seabears.campsites.api.db.mock;

import java.util.function.Consumer;

import org.springframework.core.Ordered;

import net.seabears.campsites.api.db.MockDataLoader;

import static java.util.Collections.emptyList;

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
