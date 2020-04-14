package me.jraynor.api.events;

public class WeatherFailureEvent {
    public final Throwable throwable;

    public WeatherFailureEvent(Throwable throwable) {
        this.throwable = throwable;
    }
}
