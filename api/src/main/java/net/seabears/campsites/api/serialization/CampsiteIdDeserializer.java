package net.seabears.campsites.api.serialization;

import net.seabears.campsites.db.domain.Campsite;

class CampsiteIdDeserializer extends IdDeserializer<Campsite> {
    public CampsiteIdDeserializer() {
        super(Campsite::setId, Campsite.class);
    }
}
