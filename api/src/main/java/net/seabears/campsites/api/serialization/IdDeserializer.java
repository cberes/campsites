package net.seabears.campsites.api.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.function.BiConsumer;

abstract class IdDeserializer<T> extends JsonDeserializer<T> {
    private final BiConsumer<T, Long> idSetter;
    private final Class<T> type;

    IdDeserializer(final BiConsumer<T, Long> idSetter, final Class<T> type) {
        this.idSetter = idSetter;
        this.type = type;
    }

    @Override
    public T deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
        if (parser.getCurrentValue() == null) {
            return null;
        } else {
            return createObjectWithId(parser.getLongValue());
        }
    }

    private T createObjectWithId(final long id) {
        final T value = newInstance();
        idSetter.accept(value, id);
        return value;
    }

    private T newInstance() {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Expected " + type + " to have a nullary constructor");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
