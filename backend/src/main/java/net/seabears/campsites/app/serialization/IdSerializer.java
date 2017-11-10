package net.seabears.campsites.app.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

abstract class IdSerializer<T> extends JsonSerializer<T> {
    private final Function<T, ?> idGetter;

    IdSerializer(final Function<T, ?> idGetter) {
        this.idGetter = idGetter;
    }

    @Override
    public void serialize(final T value, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        final Optional<?> id = Optional.ofNullable(value).map(idGetter);
        if (id.isPresent()) {
            gen.writeString(id.get().toString());
        } else {
            gen.writeNull();
        }
    }
}
