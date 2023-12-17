package by.it.selvanovich.weather.service;

import by.it.selvanovich.weather.exeption.ServiceException;
import by.it.selvanovich.weather.model.AverageWeatherDataModel;
import by.it.selvanovich.weather.model.WeatherDataModel;

public interface WeatherDataService {

    public WeatherDataModel getLastData(String location) throws ServiceException;

    public AverageWeatherDataModel getAverageData(String requestBody, String location) throws ServiceException;
}
