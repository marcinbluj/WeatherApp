package pl.sda.bluj.marcin.weatherapp.view;

import pl.sda.bluj.marcin.weatherapp.base.BaseView;

/**
 * Created by MSI on 22.04.2017.
 */

public interface MainView extends BaseView {
    void showWeather(String city);

    void refreshWeather();
}
