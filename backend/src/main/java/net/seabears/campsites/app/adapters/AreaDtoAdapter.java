package net.seabears.campsites.app.adapters;

import net.seabears.campsites.app.domain.Area;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AreaDtoAdapter implements Adapter<net.seabears.campsites.db.domain.Area, Area> {
    private static class View extends Area {
        private final net.seabears.campsites.db.domain.Area delegate;

        public View(final net.seabears.campsites.db.domain.Area delegate) {
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
        public String getCampgroundId() {
            return delegate.getCampground().getId().toString();
        }

        @Override
        public void setCampgroundId(final String campgroundId) {
            delegate.getCampground().setId(UUID.fromString(campgroundId));
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
    public Area adapt(final net.seabears.campsites.db.domain.Area source) {
        return new View(source);
    }
}
