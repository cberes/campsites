package net.seabears.campsites.api.controllers.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class ControllerUtils {
    private ControllerUtils() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static <T> List<T> toList(final Iterable<T> iter) {
        if (iter instanceof List) {
            return (List<T>) iter;
        } else {
            return StreamSupport.stream(iter.spliterator(), false).collect(Collectors.toList());
        }
    }
}
