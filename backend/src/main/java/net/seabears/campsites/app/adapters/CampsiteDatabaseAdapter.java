package net.seabears.campsites.app.adapters;

import net.seabears.campsites.app.domain.Campsite;
import org.springframework.stereotype.Component;

@Component
public class CampsiteDatabaseAdapter implements Adapter<Campsite, net.seabears.campsites.db.domain.Campsite> {
    private static class View extends net.seabears.campsites.db.domain.Campsite {
        private final Campsite delegate;

        View(final Campsite delegate) {
            this.delegate = delegate;
        }
    }

    @Override
    public net.seabears.campsites.db.domain.Campsite adapt(final Campsite source) {
        return new View(source);
    }
}
