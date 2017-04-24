package pl.sda.bluj.marcin.weatherapp.presenter;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import pl.sda.bluj.marcin.weatherapp.base.BasePresenter;
import pl.sda.bluj.marcin.weatherapp.model.SimpleWeather;
import pl.sda.bluj.marcin.weatherapp.model.WeatherData;
import pl.sda.bluj.marcin.weatherapp.repository.WeatherRepository;
import pl.sda.bluj.marcin.weatherapp.service.WeatherService;
import pl.sda.bluj.marcin.weatherapp.util.Constant;
import pl.sda.bluj.marcin.weatherapp.view.WeatherView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MSI on 22.04.2017.
 */

public class WeatherPresenter extends BasePresenter<WeatherView> {

    private WeatherRepository mRepository;

    private static final String URL_OPEN_WEATHER_MAP = "http://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "092d3125cdc41f1041e68d1e5c74c4f1";
    private static final String UNITS = "metric";

    public WeatherPresenter(Scheduler subscribeOn, Scheduler observeOn, WeatherRepository repository) {
        super(subscribeOn, observeOn);
        mRepository = repository;
    }

    public void loadData(String city, String locale) {
        getView().showProgressBar();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL_OPEN_WEATHER_MAP)
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);
        Observable<WeatherData> cityWeatherObs = weatherService
                .getWeatherData(city, UNITS, locale, API_KEY);

        addDisposable(cityWeatherObs
                .subscribeOn(mSubscribeOn)
                .observeOn(mObserveOn)
                .subscribe(weatherData -> {
                            SimpleWeather weather = Constant.convertWeatherDataToSimpleWeather(weatherData);
                            showWeather(weather);
                            mRepository.saveWeather(weather, city);
                        },
                        e -> {
                            getView().showAlertDialog();
                            SimpleWeather weather = mRepository.getWeather(city);
                            if (weather != null) {
                                showWeather(weather);
                                getView().showDataOutOfDateToast();
                            }
                        },
                        () -> getView().dismissProgressBar()));
    }

    public void onDialogOkClick() {
        getView().dismissProgressBar();
    }

    private void showWeather(SimpleWeather weather) {
        getView().showCityName(weather.getCityName());
        getView().showLastDataTime(weather.getLastUpdateTime());
        getView().showWeatherDescription(weather.getWeatherDescription());
        getView().showWeatherIcon(weather.getUrlIcon());
        getView().showTemperature(weather.getTemperature());
        getView().showPressure(weather.getPressure());
        getView().showWindSpeed(weather.getWindSpeed());
    }
}
