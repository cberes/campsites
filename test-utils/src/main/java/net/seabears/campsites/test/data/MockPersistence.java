package net.seabears.campsites.test.data;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static java.util.Arrays.stream;

public class MockPersistence {
    private final Map<Class<?>, Long> ids = new HashMap<>();
    private final Map<Class<?>, Field> fields = new HashMap<>();

    public <T> UnaryOperator<T> loader() {
        return this::persist;
    }

    public <T> T persist(final T obj) {
        final long id = getAndIncrementId(obj.getClass());
        getIdFunction(obj).ifPresent(field -> setId(field, obj, id));
        return obj;
    }

    private static void setId(final Field field, final Object obj, final long id) {
        try {
            field.set(obj, id);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    private long getAndIncrementId(final Class<?> type) {
        final long previousId = ids.containsKey(type) ? ids.get(type) : 0;
        final long nextId = previousId + 1L;
        ids.put(type, nextId);
        return nextId;
    }

    private Optional<Field> getIdFunction(final Object obj) {
        if (fields.containsKey(obj.getClass())) {
            return Optional.of(fields.get(obj.getClass()));
        } else {
            final Optional<Field> field = findIdFunction(obj);
            field.ifPresent(f -> saveIdFunction(obj.getClass(), f));
            return field;
        }
    }

    private void saveIdFunction(final Class<?> type, final Field field) {
        field.setAccessible(true);
        fields.put(type, field);
    }

    private static Optional<Field> findIdFunction(final Object obj) {
        final Field[] fields = obj.getClass().getDeclaredFields();
        return stream(fields)
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findAny();

    }
}
