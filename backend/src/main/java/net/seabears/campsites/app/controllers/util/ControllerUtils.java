package net.seabears.campsites.app.controllers.util;

import net.seabears.campsites.app.adapters.Adapter;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public final class ControllerUtils {
    private ControllerUtils() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static <T, I> List<T> toDtoList(final Iterable<I> iter, final Adapter<I, T> adapter) {
        return StreamSupport.stream(iter.spliterator(), false)
                .map(adapter::adapt)
                .collect(toList());
    }
}
