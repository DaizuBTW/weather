package by.it.selvanovich.weather.service.impl;

import by.it.selvanovich.weather.constants.Constants;
import by.it.selvanovich.weather.entity.WeatherData;
import by.it.selvanovich.weather.exeption.ServiceException;
import by.it.selvanovich.weather.model.AverageWeatherDataModel;
import by.it.selvanovich.weather.model.WeatherDataModel;
import by.it.selvanovich.weather.repository.DataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherDataServiceImplTest {
    private static final String DATE = "2023-12-15";
    private static final String JSON_DATA = "{\n" +
            "    \"location\": {\n" +
            "        \"region\": \"Minsk\",\n" +
            "        \"localtime\": \"2023-12-15 18:17\"\n" +
            "    },\n" +
            "    \"current\": {\n" +
            "        \"temp_c\": 4.0,\n" +
            "        \"condition\": {\n" +
            "            \"text\": \"Partly cloudy\"\n" +
            "        },\n" +
            "        \"wind_mph\": 18.6,\n" +
            "        \"pressure_mb\": 1017.0,\n" +
            "        \"humidity\": 100\n" +
            "    }\n" +
            "}";
    private static final String REQUEST_BODY = "{" +
            "    \"from\": \"2023-12-15\"," +
            "    \"to\": \"2023-12-15\"" +
            "}";
    private static final String INVALID_REQUEST_BODY = "{" +
            "    \"from\": \"15d-12m-2023y\"," +
            "    \"to\": \"15d-12m-2023y\"" +
            "}";

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private WeatherDataServiceImpl weatherDataServiceImpl;
    @InjectMocks
    private ScheduleServiceImpl scheduleServiceImpl;

    @Test
    void getLastDataShouldReturnWeatherData() {
        final WeatherData weatherData = mock(WeatherData.class);
        when(dataRepository.findTop1ByLocationContainingOrderByDateDesc("Minsk")).thenReturn(weatherData);

        try {
            final WeatherDataModel actual = weatherDataServiceImpl.getLastData("Minsk");
            assertNotNull(actual);
            assertEquals(WeatherDataModel.toModel(weatherData), actual);
            verify(dataRepository).findTop1ByLocationContainingOrderByDateDesc("Minsk");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getLastDataShouldThrowServiceException() {
        assertThrows(ServiceException.class, () -> weatherDataServiceImpl.getLastData("Minsk"), "No data found");
    }

    @Test
    void getAverageDataShouldReturnAverageWeatherData() {
        final WeatherData weatherData = mock(WeatherData.class);

        try {
            final AverageWeatherDataModel actual = weatherDataServiceImpl.getAverageData(REQUEST_BODY, "Minsk");
            assertNotNull(actual);
            AverageWeatherDataModel model = AverageWeatherDataModel.toModel(weatherData, DATE, DATE);
            model.setLocation("Minsk");
            assertEquals(model, actual);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAverageDataThrowServiceException() {
        assertThrows(ServiceException.class, () -> weatherDataServiceImpl.getAverageData(INVALID_REQUEST_BODY, "Minsk"));
    }

    @Test
    void createWeatherDataShouldReturnWeatherData() {
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN);
        try {
            final WeatherData weatherData = new WeatherData(4.0f, 18.6f, 1017.0f,
                    100, "Partly cloudy", "Minsk", format.parse("2023-12-15 18:17"));
            final WeatherData actual = scheduleServiceImpl.createWeatherData(JSON_DATA);
            assertNotNull(actual);
            assertEquals(weatherData, actual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createWeatherDataThrowServiceException() {
        assertThrows(ServiceException.class, () -> scheduleServiceImpl.createWeatherData(null), "invalid data was received");
    }
}