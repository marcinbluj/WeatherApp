package pl.sda.bluj.marcin.weatherapp.view;

import pl.sda.bluj.marcin.weatherapp.base.BaseView;
import pl.sda.bluj.marcin.weatherapp.model.WeatherData;

/**
 * Created by MSI on 22.04.2017.
 */

public interface WeatherView extends BaseView {
    void showAlertDialog();

    void dismissAlertDialog();

    void showProgressBar();

    void dismissProgressBar();

    void showCityName(String name);

    void showLastDataTime(String time);

    void showWeatherDescription(String description);

    void showWeatherIcon(String iconTag);

    void showTemperature(String temperature);

    void showPressure(String pressure);

    void showWindSpeed(String windSpeed);

    void showDataOutOfDateToast();


//    mCityTextView.setText(getName(weatherData));
//        mTemperatureTextView.setText(getTemp(weatherData));
//        mPressureTextView.setText(getPressure(weatherData));
//        mTimeTextView.setText(getTime(weatherData));
//        mWindSpeedTextView.setText(getSpeed(weatherData));
//        mDescriptionTextView.setText(getDescription(weatherData));
//        Picasso.with(getActivity())
//                .load("http://openweathermap.org/img/w/" + getIcon(weatherData) + ".png")
//                .into(mIconImageView);
}
