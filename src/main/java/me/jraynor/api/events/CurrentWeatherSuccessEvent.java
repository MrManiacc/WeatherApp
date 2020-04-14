package me.jraynor.api.events;

import me.jraynor.api.models.AccuWeatherModel;

public class CurrentWeatherSuccessEvent {
    public final AccuWeatherModel model;

    public CurrentWeatherSuccessEvent(AccuWeatherModel model) {
        this.model = model;
    }
}
