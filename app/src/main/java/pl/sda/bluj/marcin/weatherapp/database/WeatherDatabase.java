package pl.sda.bluj.marcin.weatherapp.database;

import pl.sda.bluj.marcin.weatherapp.model.SimpleWeather;

/**
 * Created by MSI on 23.04.2017.
 */

public interface WeatherDatabase {

    void saveWeather(SimpleWeather weather, String city);

    SimpleWeather getWeather(String city);
}
