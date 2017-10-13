package net.seabears.campsites.db.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = true)
public class UuidConverter implements AttributeConverter<UUID, UUID> {
    @Override
    public UUID convertToDatabaseColumn(final UUID attribute) {
        return attribute;
    }

    @Override
    public UUID convertToEntityAttribute(final UUID dbData) {
        return dbData;
    }
}
