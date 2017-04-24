package pl.sda.bluj.marcin.weatherapp.repository;

import pl.sda.bluj.marcin.weatherapp.AndroidApplication;
import pl.sda.bluj.marcin.weatherapp.database.WeatherDatabase;
import pl.sda.bluj.marcin.weatherapp.model.SimpleWeather;
import pl.sda.bluj.marcin.weatherapp.model.WeatherData;
import pl.sda.bluj.marcin.weatherapp.util.Constant;

/**
 * Created by MSI on 23.04.2017.
 */

public class WeatherRepositoryImpl implements WeatherRepository {
    private static final WeatherRepositoryImpl INSTANCE = new WeatherRepositoryImpl();
    private final WeatherDatabase mDatabase;

    private WeatherRepositoryImpl() {
        mDatabase = AndroidApplication.getDatabase();
    }

    public static WeatherRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveWeather(SimpleWeather weather, String city) {
        mDatabase.saveWeather(weather, city);
    }

    @Override
    public SimpleWeather getWeather(String city) {
        SimpleWeather weather = mDatabase.getWeather(city);
        return weather;
    }
}
