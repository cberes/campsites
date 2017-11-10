package net.seabears.campsites.api.serialization;

import net.seabears.campsites.db.domain.Campsite;

class CampsiteIdSerializer extends IdSerializer<Campsite> {
    public CampsiteIdSerializer() {
        super(Campsite::getId);
    }
}
