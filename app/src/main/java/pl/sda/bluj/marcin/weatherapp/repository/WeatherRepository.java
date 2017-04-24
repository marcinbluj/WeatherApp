package pl.sda.bluj.marcin.weatherapp.repository;

import pl.sda.bluj.marcin.weatherapp.model.SimpleWeather;

/**
 * Created by MSI on 23.04.2017.
 */

public interface WeatherRepository {

    void saveWeather(SimpleWeather weather, String city);

    SimpleWeather getWeather(String city);
}
