package pl.sda.bluj.marcin.weatherapp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by MSI on 23.04.2017.
 */

@DatabaseTable(tableName = SimpleWeather.TABLE_NAME)
public class SimpleWeather {
    static final String TABLE_NAME = "weather";

    @DatabaseField(columnName = "_id", allowGeneratedIdInsert = true, generatedId = true)
    private int id;
    @DatabaseField(columnName = "name", unique = true, canBeNull = false)
    private String mCityName;
    @DatabaseField(columnName = "time", canBeNull = false)
    private String mLastUpdateTime;
    @DatabaseField(columnName = "description", canBeNull = false)
    private String mWeatherDescription;
    @DatabaseField(columnName = "icon")
    private String mUrlIcon;
    @DatabaseField(columnName = "temperature", canBeNull = false)
    private String mTemperature;
    @DatabaseField(columnName = "pressure", canBeNull = false)
    private String mPressure;
    @DatabaseField(columnName = "wind", canBeNull = false)
    private String mWindSpeed;

    public SimpleWeather() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        this.mCityName = cityName;
    }

    public String getLastUpdateTime() {
        return mLastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.mLastUpdateTime = lastUpdateTime;
    }

    public String getWeatherDescription() {
        return mWeatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.mWeatherDescription = weatherDescription;
    }

    public String getUrlIcon() {
        return mUrlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.mUrlIcon = urlIcon;
    }

    public String getTemperature() {
        return mTemperature;
    }

    public void setTemperature(String temperature) {
        this.mTemperature = temperature;
    }

    public String getPressure() {
        return mPressure;
    }

    public void setPressure(String pressure) {
        this.mPressure = pressure;
    }

    public String getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.mWindSpeed = windSpeed;
    }
}
