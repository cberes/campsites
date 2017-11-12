package net.seabears.campsites.api.db.mock;

enum DataLoadingPriority {
    CAMPGROUND(100),
    AREA(200),
    CAMPSITE(300),
    CUSTOMER(400);

    private final int priority;

    DataLoadingPriority(final int priority) {
        this.priority = priority;
    }

    public int priority() {
        return priority;
    }
}
