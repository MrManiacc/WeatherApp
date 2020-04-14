package me.jraynor.api;

import javafx.application.Platform;
import me.jraynor.api.events.*;
import me.jraynor.api.interaces.IWeatherApi;
import me.jraynor.api.models.AccuWeather5DayModel;
import me.jraynor.api.models.AccuWeatherModel;
import me.jraynor.api.models.LocationSearchModel;
import me.jraynor.api.models.OpenWeather5DayModel;
import me.jraynor.context.Bus;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class Weather {
    private static final String API_KEY = "RAwnYDiRmR7D2SlUD7R9FpdonP1Ucbf9";
    private static final String BASE_URL = "http://dataservice.accuweather.com/";

    /**
     * This will send a request for the given city and create the proper events when successful or unsuccessful
     */
    public static void getLocation(String city) {
        IWeatherApi api = ApiService.getRetrofitInstance(BASE_URL).create(IWeatherApi.class);
        Call<List<LocationSearchModel>> call = api.getAccuWeatherCities(API_KEY, city);
        call.enqueue(new Callback<List<LocationSearchModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<LocationSearchModel>> call, @NotNull Response<List<LocationSearchModel>> response) {
                List<LocationSearchModel> models = response.body();
                Bus.post(new LocationSuccessEvent(models));
            }

            @Override
            public void onFailure(@NotNull Call<List<LocationSearchModel>> call, @NotNull Throwable throwable) {
                Bus.post(new LocationFailureEvent(throwable));
            }
        });
    }

    /**
     * This will send a request for the given city and get the corresponding weather information
     */
    public static void getWeatherForecast(String city) {
        IWeatherApi api = ApiService.getRetrofitInstance(BASE_URL).create(IWeatherApi.class);
        Call<AccuWeather5DayModel> call = api.getAccuWeatherData5days(city, API_KEY);
        call.enqueue(new Callback<AccuWeather5DayModel>() {
            @Override
            public void onResponse(@NotNull Call<AccuWeather5DayModel> call, @NotNull Response<AccuWeather5DayModel> response) {
                Platform.runLater(() -> Bus.post(new WeatherSuccessEvent(response.body())));
            }

            @Override
            public void onFailure(@NotNull Call<AccuWeather5DayModel> call, @NotNull Throwable throwable) {
                Platform.runLater(() -> Bus.post(new WeatherFailureEvent(throwable)));
            }
        });
    }


    /**
     * This will send a request for the given city and get the corresponding weather information
     */
    public static void getCurrentWeather(String city) {
        IWeatherApi api = ApiService.getRetrofitInstance(BASE_URL).create(IWeatherApi.class);
        Call<List<AccuWeatherModel>> call = api.getAccuWeatherData(city, API_KEY);
        call.enqueue(new Callback<List<AccuWeatherModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<AccuWeatherModel>> call, @NotNull Response<List<AccuWeatherModel>> response) {
                Platform.runLater(() -> {
                    assert response.body() != null;
                    Bus.post(new CurrentWeatherSuccessEvent(response.body().get(0)));
                });
            }

            @Override
            public void onFailure(@NotNull Call<List<AccuWeatherModel>> call, @NotNull Throwable throwable) {
                Platform.runLater(() -> {
                    Bus.post(new CurrentWeatherFailureEvent(throwable));
                });
            }
        });
    }
}
