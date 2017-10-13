package net.seabears.campsites.app.dao.mock;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

abstract class MockDao<T> {
    private final List<T> items;

    public MockDao(final List<T> items) {
        this.items = items;
    }

    protected abstract BiConsumer<T, String> getIdSetter();

    protected void assignIds() {
        final BiConsumer<T, String> setter = getIdSetter();
        range(0, items.size()).forEach(i -> setter.accept(items.get(i), Integer.toString(i + 1)));
    }

    protected List<T> allItems() {
        return items;
    }

    protected <V> List<T> findWith(final Function<T, V> getter, final V value) {
        return allItems().stream()
                .filter(item -> getter.apply(item).equals(value))
                .collect(toList());
    }

    protected Optional<T> find(final int index) {
        if (index >= 0 && index < items.size()) {
            return Optional.of(items.get(index));
        } else {
            return Optional.empty();
        }
    }
}
