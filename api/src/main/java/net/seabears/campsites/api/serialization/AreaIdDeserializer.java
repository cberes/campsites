package net.seabears.campsites.api.serialization;

import net.seabears.campsites.db.domain.Area;

class AreaIdDeserializer extends IdDeserializer<Area> {
    public AreaIdDeserializer() {
        super(Area::setId, Area.class);
    }
}
