package net.seabears.campsites.controllers.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private final Class<?> type;
    private final String id;

    public ResourceNotFoundException(final Class<?> type, final String id) {
        super("Could not find " + type.getSimpleName() + " resource with ID " + id);
        this.type = type;
        this.id = id;
    }
}
