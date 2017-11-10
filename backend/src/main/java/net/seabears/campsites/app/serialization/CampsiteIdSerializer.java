package net.seabears.campsites.app.serialization;

import net.seabears.campsites.db.domain.Campsite;

class CampsiteIdSerializer extends IdSerializer<Campsite> {
    public CampsiteIdSerializer() {
        super(Campsite::getId);
    }
}
