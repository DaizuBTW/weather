package by.it.selvanovich.weather.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;

@Entity
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float temperature;
    private float wind;
    private float pressure;
    private int humidity;
    private String condition;
    private String location;
    private Date date;


    public WeatherData() {
    }

    public WeatherData(float temperature, float wind, float pressure,
                       int humidity, String condition, String location, Date date) {
        this.temperature = temperature;
        this.wind = wind;
        this.pressure = pressure;
        this.humidity = humidity;
        this.condition = condition;
        this.location = location;
        this.date = date;
    }

    public WeatherData(float temperature, float wind, float pressure,
                       int humidity, String condition, String location) {
        this.temperature = temperature;
        this.wind = wind;
        this.pressure = pressure;
        this.humidity = humidity;
        this.condition = condition;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherData weatherData = (WeatherData) o;
        return Objects.equals(id, weatherData.id) &&
                Objects.equals(temperature, weatherData.temperature) &&
                Objects.equals(wind, weatherData.wind) &&
                Objects.equals(pressure, weatherData.pressure) &&
                Objects.equals(humidity, weatherData.humidity) &&
                Objects.equals(condition, weatherData.condition) &&
                Objects.equals(location, weatherData.location) &&
                Objects.equals(date, weatherData.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, temperature, wind, pressure, humidity, condition, location, date);
    }
}


