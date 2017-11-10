package net.seabears.campsites.api.controllers.util;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public final class ControllerUtils {
    private ControllerUtils() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static <T> List<T> toDtoList(final Iterable<T> iter) {
        return StreamSupport.stream(iter.spliterator(), false).collect(toList());
    }
}
