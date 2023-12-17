package by.it.selvanovich.weather.model;

import by.it.selvanovich.weather.constants.Constants;
import by.it.selvanovich.weather.entity.WeatherData;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class WeatherDataModel {

    private float temperature;
    private float wind;
    private float pressure;
    private int humidity;
    private String condition;
    private String location;
    private String date;

    public static WeatherDataModel toModel(WeatherData entity) {
        WeatherDataModel model = new WeatherDataModel();
        model.setTemperature(entity.getTemperature());
        model.setWind(entity.getWind());
        model.setPressure(entity.getPressure());
        model.setHumidity(entity.getHumidity());
        model.setCondition(entity.getCondition());
        model.setLocation(entity.getLocation());

        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN);
        if (entity.getDate() != null) {
            model.setDate(formatter.format(entity.getDate()));
        }
        return model;
    }

    public WeatherDataModel() {
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getWind() {
        return wind;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
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
        return Objects.equals(temperature, weatherData.temperature) &&
                Objects.equals(wind, weatherData.wind) &&
                Objects.equals(pressure, weatherData.pressure) &&
                Objects.equals(humidity, weatherData.humidity) &&
                Objects.equals(condition, weatherData.condition) &&
                Objects.equals(location, weatherData.location) &&
                Objects.equals(date, weatherData.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, wind, pressure, humidity, condition, location, date);
    }

}
