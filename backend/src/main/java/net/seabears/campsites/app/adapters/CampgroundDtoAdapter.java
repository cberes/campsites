package net.seabears.campsites.app.adapters;

import net.seabears.campsites.app.domain.Campground;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CampgroundDtoAdapter implements Adapter<net.seabears.campsites.db.domain.Campground, Campground> {
    private static class View extends Campground {
        private final net.seabears.campsites.db.domain.Campground delegate;

        View(final net.seabears.campsites.db.domain.Campground delegate) {
            this.delegate = delegate;
        }

        @Override
        public String getId() {
            return delegate.getId().toString();
        }

        @Override
        public void setId(final String id) {
            delegate.setId(UUID.fromString(id));
        }

        @Override
        public String getName() {
            return delegate.getName();
        }

        @Override
        public void setName(final String name) {
            delegate.setName(name);
        }

        @Override
        public String getDescription() {
            return delegate.getDescription();
        }

        @Override
        public void setDescription(final String description) {
            delegate.setDescription(description);
        }
    }

    @Override
    public Campground adapt(final net.seabears.campsites.db.domain.Campground source) {
        return new View(source);
    }
}
