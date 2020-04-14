package me.jraynor.api.events;

import me.jraynor.api.models.AccuWeather5DayModel;

public class WeatherSuccessEvent {
    public final AccuWeather5DayModel model;

    public WeatherSuccessEvent(AccuWeather5DayModel model) {
        this.model = model;
    }
}
