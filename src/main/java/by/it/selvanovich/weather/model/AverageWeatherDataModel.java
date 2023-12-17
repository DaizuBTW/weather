package by.it.selvanovich.weather.model;

import by.it.selvanovich.weather.entity.WeatherData;

import java.util.Objects;

public class AverageWeatherDataModel {

    private float averageTemperatureC;
    private float averageWindMH;
    private float averagePressureMB;
    private int averageHumidity;
    private String averageCondition;
    private String location;
    private String date;

    public static AverageWeatherDataModel toModel(WeatherData entity, String from, String to) {
        AverageWeatherDataModel model = new AverageWeatherDataModel();
        model.setAverageTemperatureC(entity.getTemperature());
        model.setAverageWindMH(entity.getWind());
        model.setAveragePressureMB(entity.getPressure());
        model.setAverageHumidity(entity.getHumidity());
        model.setAverageCondition(entity.getCondition());
        model.setLocation(entity.getLocation());
        model.setDate("from " + from + " to " + to);

        return model;
    }

    public AverageWeatherDataModel() {
    }

    public float getAverageTemperatureC() {
        return averageTemperatureC;
    }

    public void setAverageTemperatureC(float averageTemperatureC) {
        this.averageTemperatureC = averageTemperatureC;
    }

    public float getAverageWindMH() {
        return averageWindMH;
    }

    public void setAverageWindMH(float averageWindMH) {
        this.averageWindMH = averageWindMH;
    }

    public float getAveragePressureMB() {
        return averagePressureMB;
    }

    public void setAveragePressureMB(float averagePressureMB) {
        this.averagePressureMB = averagePressureMB;
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
        return Objects.equals(averageTemperatureC, weatherData.averageTemperatureC) &&
                Objects.equals(averageWindMH, weatherData.averageWindMH) &&
                Objects.equals(averagePressureMB, weatherData.averagePressureMB) &&
                Objects.equals(averageHumidity, weatherData.averageHumidity) &&
                Objects.equals(averageCondition, weatherData.averageCondition) &&
                Objects.equals(location, weatherData.location) &&
                Objects.equals(date, weatherData.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageTemperatureC, averageWindMH, averagePressureMB, averageHumidity, averageCondition, location, date);
    }
}
