package net.seabears.campsites.app.adapters;

import net.seabears.campsites.app.domain.Campground;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CampgroundDatabaseAdapter implements Adapter<Campground, net.seabears.campsites.db.domain.Campground> {
    private static class View extends net.seabears.campsites.db.domain.Campground {
        private final Campground delegate;

        View(final Campground delegate) {
            this.delegate = delegate;
        }

        @Override
        public UUID getId() {
            return Optional.ofNullable(delegate).map(Campground::getId).map(UUID::fromString).orElse(null);
        }

        @Override
        public void setId(final UUID id) {
            Optional.ofNullable(id).map(UUID::toString).ifPresent(delegate::setId);
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
    public net.seabears.campsites.db.domain.Campground adapt(final Campground source) {
        return new View(source);
    }
}
