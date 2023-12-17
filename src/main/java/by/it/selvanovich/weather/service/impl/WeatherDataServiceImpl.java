package by.it.selvanovich.weather.service.impl;

import by.it.selvanovich.weather.constants.Constants;
import by.it.selvanovich.weather.entity.WeatherData;
import by.it.selvanovich.weather.exeption.ServiceException;
import by.it.selvanovich.weather.model.AverageWeatherDataModel;
import by.it.selvanovich.weather.model.WeatherDataModel;
import by.it.selvanovich.weather.repository.DataRepository;
import by.it.selvanovich.weather.service.WeatherDataService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private static final Logger LOG = LogManager.getLogger(WeatherDataServiceImpl.class);

    @Autowired
    private DataRepository dataRepository;

    @Override
    public WeatherDataModel getLastData(String location) throws ServiceException {
        WeatherData entity = dataRepository.findTop1ByLocationContainingOrderByDateDesc(location);
        if (entity == null) {
            LOG.error("No data found");
            throw new ServiceException("No data found");
        } else {
            return WeatherDataModel.toModel(entity);
        }
    }

    @Override
    public AverageWeatherDataModel getAverageData(String requestBody, String location) throws ServiceException {
        JsonElement jElement = new JsonParser().parse(requestBody);
        JsonObject jObject = jElement.getAsJsonObject();
        String from = jObject.get(Constants.J_OBJECT_VALUE_FROM).getAsString();
        String to = jObject.get(Constants.J_OBJECT_VALUE_TO).getAsString();

        SimpleDateFormat format = new SimpleDateFormat(Constants.AVERAGE_DATE_FORMAT_PATTERN);
        try {
            Timestamp dateFrom = new Timestamp(format.parse(from).getTime());
            Timestamp dateTo = new Timestamp(format.parse(to).getTime());

            float temperature = dataRepository.findAvgTemperatureByDate(dateFrom, dateTo, location);
            float wind = dataRepository.findAvgWindByDate(dateFrom, dateTo, location);
            float pressure = dataRepository.findAvgPressureByDate(dateFrom, dateTo, location);
            int humidity = dataRepository.findAvgHumidityByDate(dateFrom, dateTo, location);
            String condition = dataRepository.findAvgConditionByDate(dateFrom, dateTo, location);
            return AverageWeatherDataModel.toModel(new WeatherData(temperature,
                    wind, pressure, humidity, condition, location), from, to);
        } catch (Exception e) {
            LOG.error("Invalid data", e);
            throw new ServiceException("An error has occurred! Invalid data of format, the date format should be: yyyy-MM-dd");
        }
    }
}
