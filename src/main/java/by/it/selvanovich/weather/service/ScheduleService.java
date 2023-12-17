package by.it.selvanovich.weather.service;

import by.it.selvanovich.weather.entity.WeatherData;
import by.it.selvanovich.weather.exeption.ServiceException;
import by.it.selvanovich.weather.model.AverageWeatherDataModel;

public interface ScheduleService {

    public void getWeatherJson() throws ServiceException;

    public WeatherData createWeatherData(String jData) throws ServiceException;
}
