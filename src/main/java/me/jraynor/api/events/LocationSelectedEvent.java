package me.jraynor.api.events;

public class LocationSelectedEvent {
    public final String locationKey;

    public LocationSelectedEvent(String locationKey) {
        this.locationKey = locationKey;
    }
}
