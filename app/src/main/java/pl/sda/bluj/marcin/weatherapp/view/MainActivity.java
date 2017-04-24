package pl.sda.bluj.marcin.weatherapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.sda.bluj.marcin.weatherapp.R;
import pl.sda.bluj.marcin.weatherapp.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainActivityPresenter mPresenter;

    @BindView(R.id.main_spinner)
    Spinner mSpinner;

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainActivityPresenter();
        mPresenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupSpinner();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCurrentFragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.remove(mCurrentFragment);
            fragmentTransaction.commit();
        }
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cities,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = (String) parent.getItemAtPosition(position);
                mPresenter.onItemSelected(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showWeather(String city) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (mCurrentFragment != null) {
            fragmentTransaction.remove(mCurrentFragment);
        }
        mCurrentFragment = WeatherFragment.newInstance(city);
        fragmentTransaction.add(R.id.main_fragment_container, mCurrentFragment);
        fragmentTransaction.commit();
    }

    @OnClick(R.id.main_refresh_button)
    public void onRefreshClicked() {
        mPresenter.onRefreshClicked();
        refreshWeather();
    }

    @Override
    public void refreshWeather() {
        if (mSpinner.getOnItemSelectedListener() != null) {
            mSpinner.getOnItemSelectedListener().onItemSelected(mSpinner, mSpinner.getRootView(),
                    mSpinner.getSelectedItemPosition(), mSpinner.getSelectedItemId());
        }
    }
}
