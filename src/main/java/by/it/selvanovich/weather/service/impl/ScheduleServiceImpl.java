package by.it.selvanovich.weather.service.impl;

import by.it.selvanovich.weather.constants.Constants;
import by.it.selvanovich.weather.entity.WeatherData;
import by.it.selvanovich.weather.exeption.ServiceException;
import by.it.selvanovich.weather.repository.DataRepository;
import by.it.selvanovich.weather.service.ScheduleService;
import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger LOG = LogManager.getLogger(WeatherDataServiceImpl.class);

    @Autowired
    private DataRepository dataRepository;
    private final OkHttpClient client = new OkHttpClient();

    @Override
    @Scheduled(cron = "${com.scheduled.cron}")
    public void getWeatherJson() throws ServiceException {
        Request request = new Request.Builder()
                .url(Constants.RAPID_API_URL)
                .get()
                .addHeader("X-RapidAPI-Key", Constants.RAPID_API_KEY)
                .addHeader("X-RapidAPI-Host", Constants.RAPID_API_HOST)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                String jsonData = response.body().string();
                dataRepository.save(createWeatherData(jsonData));
                LOG.info("Data from a third-party resource has been successfully saved to the database");
            }
        } catch (IOException e) {
            LOG.error("An error occurred while receiving the data", e);
            throw new ServiceException("An error occurred while receiving the data");
        }
    }

    @Override
    public WeatherData createWeatherData(String jData) throws ServiceException {
        try {
            JsonElement jElement = new JsonParser().parse(jData);
            JsonObject jObject = jElement.getAsJsonObject();
            jObject = jObject.getAsJsonObject(Constants.J_OBJECT_CURRENT);
            String temperature = jObject.get(Constants.J_OBJECT_VALUE_TEMPERATURE).getAsString();
            String wind = jObject.get(Constants.J_OBJECT_VALUE_WIND).getAsString();
            String pressure = jObject.get(Constants.J_OBJECT_VALUE_PRESSURE).getAsString();
            String humidity = jObject.get(Constants.J_OBJECT_VALUE_HUMIDITY).getAsString();
            jObject = jObject.getAsJsonObject(Constants.J_OBJECT_CONDITION);
            String condition = jObject.get(Constants.J_OBJECT_VALUE_CONDITION).getAsString();
            jObject = jElement.getAsJsonObject();
            jObject = jObject.getAsJsonObject(Constants.J_OBJECT_LOCATION);
            String location = jObject.get(Constants.J_OBJECT_VALUE_REGION).getAsString();
            String date = jObject.get(Constants.J_OBJECT_VALUE_LOCALTIME).getAsString();
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN);

            return new WeatherData(Float.parseFloat(temperature), Float.parseFloat(wind),
                    Float.parseFloat(pressure), Integer.parseInt(humidity),
                    condition, location, format.parse(date));
        } catch (Exception e) {
            LOG.error("invalid data was received", e);
            throw new ServiceException("invalid data was received");
        }
    }

}
