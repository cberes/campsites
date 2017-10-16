package net.seabears.campsites.app.adapters;

import net.seabears.campsites.app.domain.Campsite;
import org.springframework.stereotype.Component;

@Component
public class CampsiteDtoAdapter implements Adapter<net.seabears.campsites.db.domain.Campsite, Campsite> {
    private static class View extends Campsite {
        private final net.seabears.campsites.db.domain.Campsite delegate;

        View(final net.seabears.campsites.db.domain.Campsite delegate) {
            this.delegate = delegate;
        }
    }

    @Override
    public Campsite adapt(final net.seabears.campsites.db.domain.Campsite source) {
        return new View(source);
    }
}
