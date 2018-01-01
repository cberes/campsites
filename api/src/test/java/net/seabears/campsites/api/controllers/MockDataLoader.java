package net.seabears.campsites.api.controllers;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.db.domain.Campsite;
import net.seabears.campsites.db.domain.Customer;
import net.seabears.campsites.test.data.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

final class MockDataLoader {
    private MockDataLoader() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    static List<Campground> loadCampgrounds() {
        final MockPersistence persist = new MockPersistence();
        return MockCampgroundData.load(persist.loader());
    }

    static List<Area> loadAreas() {
        final MockPersistence persist = new MockPersistence();
        final List<Campground> campgrounds = MockCampgroundData.load(persist.loader());
        return MockAreaData.load(persist.loader(), campgrounds);
    }

    static List<Area> loadAreas(final long campgroundId) {
        return loadAreas().stream()
                .filter(area -> area.getCampground().getId() == campgroundId)
                .collect(toList());
    }

    static List<Campsite> loadCampsites() {
        final MockPersistence persist = new MockPersistence();
        final List<Campground> campgrounds = MockCampgroundData.load(persist.loader());
        final List<Area> areas = MockAreaData.load(persist.loader(), campgrounds);
        return MockCampsiteData.load(persist.loader(), areas);
    }

    static List<Campsite> loadCampsites(final long campgroundId) {
        return loadCampsites().stream()
                .filter(campsite -> campsite.getCampground().getId() == campgroundId)
                .collect(toList());
    }

    static List<Customer> loadCustomers() {
        final MockPersistence persist = new MockPersistence();
        return MockCustomerData.load(persist.loader());
    }

    static Optional<Campground> getCampground(final int index) {
        return get(index, loadCampgrounds());
    }

    private static <T> Optional<T> get(final int index, final List<T> data) {
        return index < 0 || index >= data.size() ? Optional.empty() : Optional.of(data.get(index));
    }

    static Optional<Area> getArea(final int index) {
        return get(index, loadAreas());
    }

    static Optional<Campsite> getCampsite(final int index) {
        return get(index, loadCampsites());
    }

    static Optional<Customer> getCustomer(final int index) {
        return get(index, loadCustomers());
    }
}
