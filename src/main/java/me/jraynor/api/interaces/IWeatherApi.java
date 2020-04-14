package me.jraynor.api.interaces;

import me.jraynor.api.models.AccuWeather5DayModel;
import me.jraynor.api.models.AccuWeatherModel;
import me.jraynor.api.models.LocationSearchModel;
import me.jraynor.api.models.OpenWeather5DayModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * The main router for the weather api
 */
public interface IWeatherApi {
    @GET("locations/v1/cities/autocomplete")
    Call<List<LocationSearchModel>> getAccuWeatherCities(@Query("apikey") String appId, @Query("q") String query);

    @GET("forecasts/v1/daily/5day/{key}?metric=false")
    Call<AccuWeather5DayModel> getAccuWeatherData5days(@Path("key") String cityKey, @Query("apikey") String appId);

    @GET("currentconditions/v1/{key}")
    Call<List<AccuWeatherModel>> getAccuWeatherData(@Path("key") String cityKey, @Query("apikey") String appId);

}
