package by.it.selvanovich.weather.model;

import by.it.selvanovich.weather.constants.Constants;
import by.it.selvanovich.weather.entity.WeatherData;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class WeatherDataModel {

    private float temperatureC;
    private float windMH;
    private float pressureMB;
    private int humidity;
    private String condition;
    private String location;
    private String date;

    public static WeatherDataModel toModel(WeatherData entity) {
        WeatherDataModel model = new WeatherDataModel();
        model.setTemperatureC(entity.getTemperature());
        model.setWindMH(entity.getWind());
        model.setPressureMB(entity.getPressure());
        model.setHumidity(entity.getHumidity());
        model.setCondition(entity.getCondition());
        model.setLocation(entity.getLocation());

        if (entity.getDate() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN);
            model.setDate(formatter.format(entity.getDate()));
        }
        return model;
    }

    public WeatherDataModel() {
    }

    public float getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(float temperatureC) {
        this.temperatureC = temperatureC;
    }

    public float getWindMH() {
        return windMH;
    }

    public void setWindMH(float windMH) {
        this.windMH = windMH;
    }

    public float getPressureMB() {
        return pressureMB;
    }

    public void setPressureMB(float pressureMB) {
        this.pressureMB = pressureMB;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherDataModel weatherData = (WeatherDataModel) o;
        return Objects.equals(temperatureC, weatherData.temperatureC) &&
                Objects.equals(windMH, weatherData.windMH) &&
                Objects.equals(pressureMB, weatherData.pressureMB) &&
                Objects.equals(humidity, weatherData.humidity) &&
                Objects.equals(condition, weatherData.condition) &&
                Objects.equals(location, weatherData.location) &&
                Objects.equals(date, weatherData.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperatureC, windMH, pressureMB, humidity, condition, location, date);
    }

}
