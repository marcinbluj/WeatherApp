package pl.sda.bluj.marcin.weatherapp;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

import pl.sda.bluj.marcin.weatherapp.database.WeatherDatabase;
import pl.sda.bluj.marcin.weatherapp.database.WeatherDatabaseImpl;

/**
 * Created by MSI on 23.04.2017.
 */

public class AndroidApplication extends Application {

    private static WeatherDatabase mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = new WeatherDatabaseImpl(this);
    }

    public static WeatherDatabase getDatabase() {
        return mDatabase;
    }
}
