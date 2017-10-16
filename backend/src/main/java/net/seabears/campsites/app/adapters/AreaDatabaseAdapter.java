package net.seabears.campsites.app.adapters;

import net.seabears.campsites.app.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AreaDatabaseAdapter implements Adapter<Area, net.seabears.campsites.db.domain.Area> {
    private static class View extends net.seabears.campsites.db.domain.Area {
        private final Area delegate;

        View(final Area delegate) {
            this.delegate = delegate;
        }

        @Override
        public UUID getId() {
            return Optional.ofNullable(delegate).map(Area::getId).map(UUID::fromString).orElse(null);
        }

        @Override
        public void setId(final UUID id) {
            Optional.ofNullable(id).map(UUID::toString).ifPresent(delegate::setId);
        }

        @Override
        public Campground getCampground() {
            return Optional.ofNullable(delegate.getCampgroundId()).map(View::newCampground).orElse(null);
        }

        private static Campground newCampground(final String id) {
            final Campground campground = new Campground();
            campground.setId(UUID.fromString(id));
            return campground;
        }

        @Override
        public void setCampground(final Campground campground) {
            delegate.setCampgroundId(getCampgroundId(campground));
        }

        private static String getCampgroundId(final Campground campground) {
            if (campground == null || campground.getId() == null) {
                return null;
            } else {
                return campground.getId().toString();
            }
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public void setActive(final boolean active) {
            throw new UnsupportedOperationException("cannot change active");
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
    public net.seabears.campsites.db.domain.Area adapt(final Area source) {
        return new View(source);
    }
}
