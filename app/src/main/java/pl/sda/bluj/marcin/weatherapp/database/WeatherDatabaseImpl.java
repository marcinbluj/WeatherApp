package pl.sda.bluj.marcin.weatherapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import pl.sda.bluj.marcin.weatherapp.model.SimpleWeather;

/**
 * Created by MSI on 23.04.2017.
 */

public class WeatherDatabaseImpl extends OrmLiteSqliteOpenHelper implements WeatherDatabase {

    private static final String NAME = "weatherDatabase";
    private static final int VERSION = 1;
    private RuntimeExceptionDao<SimpleWeather, Integer> mWeatherDao;

    public WeatherDatabaseImpl(Context context) {
        super(context, NAME, null, VERSION);
        mWeatherDao = getRuntimeExceptionDao(SimpleWeather.class);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, SimpleWeather.class);
        } catch (SQLException e) {
            Log.e("OrmLiteDatabase", "Error onCreate", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, SimpleWeather.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveWeather(SimpleWeather weather, String city) {
        List<SimpleWeather> simpleWeatherList = mWeatherDao.queryForEq("name", city);
        if (simpleWeatherList.size() > 0) {
            SimpleWeather updatedWeather = simpleWeatherList.get(0);
            updatedWeather.setLastUpdateTime(weather.getLastUpdateTime());
            updatedWeather.setWeatherDescription(weather.getWeatherDescription());
            updatedWeather.setUrlIcon(weather.getWeatherDescription());
            updatedWeather.setTemperature(weather.getTemperature());
            updatedWeather.setPressure(weather.getPressure());
            updatedWeather.setWindSpeed(weather.getWindSpeed());
            mWeatherDao.update(updatedWeather);
        } else {
            weather.setCityName(city);
            mWeatherDao.create(weather);
        }
    }

    @Override
    public SimpleWeather getWeather(String city) {
        List<SimpleWeather> simpleWeatherList = mWeatherDao.queryForEq("name", city);
        if (simpleWeatherList.size() > 0) {
            return simpleWeatherList.get(0);
        } else {
            return null;
        }
    }
}
