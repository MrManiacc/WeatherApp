package me.jraynor.logic;

import com.google.common.eventbus.Subscribe;
import javafx.scene.control.Label;
import me.jraynor.annotations.Injectable;
import me.jraynor.api.Weather;
import me.jraynor.api.events.*;
import me.jraynor.api.models.AccuWeather5DayModel;
import me.jraynor.api.models.AccuWeatherModel;
import me.jraynor.api.models.LocationSearchModel;
import org.apache.commons.lang3.text.WordUtils;
import sun.jvm.hotspot.debugger.cdbg.basic.LazyBlockSym;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

/**
 * THis class handle the updating of the label values inside the respecitve panes
 */
@Injectable
public class ViewHandler {
    Label firstDayLabel;
    Label firstHighLabel;
    Label firstLowLabel;
    Label firstDayTemp;
    Label secondDayLabel;
    Label secondHighLabel;
    Label secondLowLabel;
    Label thirdDayLabel;
    Label thirdHighLabel;
    Label thirdLowLabel;

    /**
     * This will update the forecast for today plus two more days
     */
    @Subscribe
    public void onWeatherSuccess(WeatherSuccessEvent event) {
        AccuWeather5DayModel model = event.model;
        for (int i = 0; i < model.getDailyForecasts().size(); i++) {
            processForecast(model.getDailyForecasts().get(i), i);
        }
    }

    /**
     * This will update the (today) weather component when it's gotten from the database
     */
    @Subscribe
    public void onCurrentWeatherSuccess(CurrentWeatherSuccessEvent event) {
        AccuWeatherModel model = event.model;
        int temp = (int) Math.round(model.getTemperature().getImperial().getValue());
        firstDayTemp.setText(temp + "°F");
    }

    /**
     * This will update the display with the correct data for the forecast
     */
    private void processForecast(AccuWeather5DayModel.DailyForecast forecast, int id) {
        String date = WordUtils.capitalize(getDayOfWeek(forecast.getEpochDate()).name().toLowerCase());
        String high = (Math.round(forecast.getTemperature().getMaximum().getValue())) + "°F";
        String low = (Math.round(forecast.getTemperature().getMinimum().getValue())) + "°F";
        switch (id) {
            case 0:
                firstDayLabel.setText("Today");
                firstHighLabel.setText(high);
                firstLowLabel.setText(low);
                break;
            case 1:
                secondDayLabel.setText(date);
                secondHighLabel.setText(high);
                secondLowLabel.setText(low);
                break;
            case 2:
                thirdDayLabel.setText(date);
                thirdHighLabel.setText(high);
                thirdLowLabel.setText(low);
                break;
        }
    }

    /**
     * Converts the epoch into a day of the week
     */
    public static DayOfWeek getDayOfWeek(long epochSecond) {
        return Instant.ofEpochSecond(epochSecond).atOffset(ZoneOffset.UTC).getDayOfWeek();
    }
}
