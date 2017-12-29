package net.seabears.campsites.api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

abstract class IdSerializer<T> extends JsonSerializer<T> {
    private final Function<T, Long> idGetter;

    IdSerializer(final Function<T, Long> idGetter) {
        this.idGetter = idGetter;
    }

    @Override
    public void serialize(final T value, final JsonGenerator gen, final SerializerProvider sers) throws IOException {
        final Optional<Long> id = Optional.ofNullable(value).map(idGetter);
        if (id.isPresent()) {
            gen.writeNumber(id.get());
        } else {
            gen.writeNull();
        }
    }
}
