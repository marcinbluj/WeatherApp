package pl.sda.bluj.marcin.weatherapp.service;

import io.reactivex.Observable;
import pl.sda.bluj.marcin.weatherapp.model.WeatherData;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MSI on 20.04.2017.
 */

public interface WeatherService {

    @GET("find?")
    Observable<WeatherData> getWeatherData(
            @Query("q") String q,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("appid") String apiKey);
}
