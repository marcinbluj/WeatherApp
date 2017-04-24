package pl.sda.bluj.marcin.weatherapp.presenter;

import pl.sda.bluj.marcin.weatherapp.base.BasePresenter;
import pl.sda.bluj.marcin.weatherapp.view.MainView;

/**
 * Created by MSI on 22.04.2017.
 */

public class MainActivityPresenter extends BasePresenter<MainView>{
    public void onItemSelected(String city) {
        getView().showWeather(city);
    }

    public void onRefreshClicked() {
        getView().refreshWeather();
    }
}
