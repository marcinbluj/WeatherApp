package pl.sda.bluj.marcin.weatherapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.sda.bluj.marcin.weatherapp.AndroidApplication;
import pl.sda.bluj.marcin.weatherapp.R;
import pl.sda.bluj.marcin.weatherapp.presenter.WeatherPresenter;
import pl.sda.bluj.marcin.weatherapp.repository.WeatherRepository;
import pl.sda.bluj.marcin.weatherapp.repository.WeatherRepositoryImpl;

/**
 * Created by MSI on 20.04.2017.
 */

public class WeatherFragment extends Fragment implements WeatherView {
    public static final String PL_LOCALE_DISPLAY_COUNTRY = "Polska";
    public static final String PL = "pl";
    public static final String EN = "en";
    public static final String ARG_PARAM = "cityParam";

    private WeatherPresenter mPresenter;

    @BindView(R.id.weather_city)
    TextView mCityTextView;
    @BindView(R.id.weather_time)
    TextView mTimeTextView;
    @BindView(R.id.weather_icon)
    ImageView mIconImageView;
    @BindView(R.id.weather_description)
    TextView mDescriptionTextView;
    @BindView(R.id.weather_temp)
    TextView mTemperatureTextView;
    @BindView(R.id.weather_pressure)
    TextView mPressureTextView;
    @BindView(R.id.weather_speed)
    TextView mWindSpeedTextView;

    @BindView(R.id.weather_progress_bar)
    ProgressBar mProgressBar;

    private String mLocale;

    private String mCity;

    private AlertDialog mAlertDialog;

    public WeatherFragment() {
    }

    public static WeatherFragment newInstance(String cityParam) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, cityParam);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WeatherRepository repository = WeatherRepositoryImpl.getInstance();
        mPresenter = new WeatherPresenter(Schedulers.io(), AndroidSchedulers.mainThread(), repository);
        mPresenter.setView(this);

        if (getArguments() != null) {
            mCity = getArguments().getString(ARG_PARAM);
        }

        mLocale = (Locale.getDefault().getDisplayCountry().equals(PL_LOCALE_DISPLAY_COUNTRY)) ?
                PL : EN;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        mPresenter.loadData(mCity, mLocale);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.clearContainer();
        dismissAlertDialog();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage(R.string.error)
                .setPositiveButton(android.R.string.ok, (dialog1, which) -> mPresenter.onDialogOkClick())
                .create();
        mAlertDialog = dialog.show();
    }

    @Override
    public void dismissAlertDialog() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.cancel();
        }
    }

    @Override
    public void showCityName(String name) {
        mCityTextView.setText(name);
    }

    @Override
    public void showLastDataTime(String time) {
        mTimeTextView.setText(time);
    }

    @Override
    public void showWeatherDescription(String description) {
        mDescriptionTextView.setText(description);
    }

    @Override
    public void showWeatherIcon(String iconUrl) {
        Picasso.with(getActivity())
                .load(iconUrl)
                .into(mIconImageView);
    }

    @Override
    public void showTemperature(String temperature) {
        String text = getString(R.string.temp) + ": " + String.valueOf(temperature) + " C";
        mTemperatureTextView.setText(text);
    }

    @Override
    public void showPressure(String pressure) {
        String text = getString(R.string.pressure) + ": " + String.valueOf(pressure) + " hPa";
        mPressureTextView.setText(text);
    }

    @Override
    public void showWindSpeed(String windSpeed) {
        String text = getString(R.string.wind_speed) + ": " + String.valueOf(windSpeed) + " C";
        mWindSpeedTextView.setText(text);
    }

    @Override
    public void showDataOutOfDateToast() {
        Toast.makeText(getActivity(), R.string.outdated_info, Toast.LENGTH_LONG).show();
    }
}
