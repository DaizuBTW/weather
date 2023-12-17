package by.it.selvanovich.weather.model;

import by.it.selvanovich.weather.constants.Constants;
import by.it.selvanovich.weather.entity.WeatherData;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class AverageWeatherDataModel {

    private float averageTemperature;
    private float averageWind;
    private float averagePressure;
    private int averageHumidity;
    private String averageCondition;
    private String location;
    private String date;

    public static AverageWeatherDataModel toModel(WeatherData entity, String from, String to) {
        AverageWeatherDataModel model = new AverageWeatherDataModel();
        model.setAverageTemperature(entity.getTemperature());
        model.setAverageWind(entity.getWind());
        model.setAveragePressure(entity.getPressure());
        model.setAverageHumidity(entity.getHumidity());
        model.setAverageCondition(entity.getCondition());
        model.setLocation(entity.getLocation());
        model.setDate("from " + from + " to " + to);

        return model;
    }

    public AverageWeatherDataModel() {
    }

    public float getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(float averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public float getAverageWind() {
        return averageWind;
    }

    public void setAverageWind(float averageWind) {
        this.averageWind = averageWind;
    }

    public float getAveragePressure() {
        return averagePressure;
    }

    public void setAveragePressure(float averagePressure) {
        this.averagePressure = averagePressure;
    }

    public int getAverageHumidity() {
        return averageHumidity;
    }

    public void setAverageHumidity(int averageHumidity) {
        this.averageHumidity = averageHumidity;
    }

    public String getAverageCondition() {
        return averageCondition;
    }

    public void setAverageCondition(String averageCondition) {
        this.averageCondition = averageCondition;
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
        AverageWeatherDataModel weatherData = (AverageWeatherDataModel) o;
        return Objects.equals(averageTemperature, weatherData.averageTemperature) &&
                Objects.equals(averageWind, weatherData.averageWind) &&
                Objects.equals(averagePressure, weatherData.averagePressure) &&
                Objects.equals(averageHumidity, weatherData.averageHumidity) &&
                Objects.equals(averageCondition, weatherData.averageCondition) &&
                Objects.equals(location, weatherData.location) &&
                Objects.equals(date, weatherData.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageTemperature, averageWind, averagePressure, averageHumidity, averageCondition, location, date);
    }
}
