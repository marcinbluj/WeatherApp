package pl.sda.bluj.marcin.weatherapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pl.sda.bluj.marcin.weatherapp.model.SimpleWeather;
import pl.sda.bluj.marcin.weatherapp.model.WeatherData;

/**
 * Created by MSI on 23.04.2017.
 */

public class Constant {
    private Constant() {}

    public static SimpleWeather convertWeatherDataToSimpleWeather(WeatherData weatherData) {
        SimpleWeather weather = new SimpleWeather();
        weather.setCityName(getName(weatherData));
        weather.setLastUpdateTime(getTime(weatherData));
        weather.setWeatherDescription(getDescription(weatherData));
        weather.setUrlIcon(getIcon(weatherData));
        weather.setTemperature(getTemp(weatherData));
        weather.setPressure(getPressure(weatherData));
        weather.setWindSpeed(getSpeed(weatherData));

        return weather;
    }

    private static String getDescription(WeatherData weatherData) {
        return weatherData.getList().get(0)
                .getWeather()
                .get(0)
                .getDescription();
    }

    private static String getIcon(WeatherData weatherData) {
        String iconTag = weatherData.getList().get(0)
                .getWeather()
                .get(0)
                .getIcon();

        return "http://openweathermap.org/img/w/" + iconTag + ".png";
    }

    private static String getTime(WeatherData weatherData) {
        Integer time = weatherData.getList().get(0)
                .getDt();

        SimpleDateFormat formatter = new SimpleDateFormat("d.MM, HH:mm", Locale.getDefault());
        Date date = new Date((long) time * 1000);

        return formatter.format(date);
    }

    private static String getPressure(WeatherData weatherData) {
        Double pressure = weatherData.getList().get(0)
                .getMain()
                .getPressure();

        return String.valueOf(pressure);
    }

    private static String getTemp(WeatherData weatherData) {
        Double temp = weatherData.getList().get(0)
                .getMain()
                .getTemp();

        return String.valueOf(temp);
    }

    private static String getSpeed(WeatherData weatherData) {
        Double speed = weatherData.getList().get(0)
                .getWind()
                .getSpeed();

        return String.valueOf(speed);
    }

    private static String getName(WeatherData weatherData) {
        return weatherData.getList().get(0)
                .getName();
    }
}
