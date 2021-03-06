package net.seabears.campsites.api.controllers.exceptions;

public class BadArgumentException extends RuntimeException {
    private final String field;
    private final String reason;

    public BadArgumentException(final String field, final String reason) {
        super("Bad argument for " + field + ": " + reason);
        this.field = field;
        this.reason = reason;
    }

    public String getField() {
        return field;
    }

    public String getReason() {
        return reason;
    }
}
