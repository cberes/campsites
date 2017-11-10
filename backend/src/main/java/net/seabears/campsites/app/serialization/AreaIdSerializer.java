package net.seabears.campsites.app.serialization;

import net.seabears.campsites.db.domain.Area;

class AreaIdSerializer extends IdSerializer<Area> {
    public AreaIdSerializer() {
        super(Area::getId);
    }
}
