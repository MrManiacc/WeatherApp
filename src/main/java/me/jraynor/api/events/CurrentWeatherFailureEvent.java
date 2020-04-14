package me.jraynor.api.events;

public class CurrentWeatherFailureEvent {
    public final Throwable throwable;

    public CurrentWeatherFailureEvent(Throwable throwable) {
        this.throwable = throwable;
    }
}
