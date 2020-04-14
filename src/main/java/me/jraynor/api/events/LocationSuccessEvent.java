package me.jraynor.api.events;

import me.jraynor.api.models.LocationSearchModel;

import java.util.List;

public final class LocationSuccessEvent {
    public final List<LocationSearchModel> models;

    public LocationSuccessEvent(List<LocationSearchModel> models) {
        this.models = models;
    }
}
