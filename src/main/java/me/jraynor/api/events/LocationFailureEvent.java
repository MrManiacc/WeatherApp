package me.jraynor.api.events;

public class LocationFailureEvent {
    public final Throwable throwable;

    public LocationFailureEvent(Throwable throwable) {
        this.throwable = throwable;
    }
}
