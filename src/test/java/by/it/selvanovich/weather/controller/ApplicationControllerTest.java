package by.it.selvanovich.weather.controller;

import by.it.selvanovich.weather.exeption.ServiceException;
import by.it.selvanovich.weather.model.AverageWeatherDataModel;
import by.it.selvanovich.weather.model.WeatherDataModel;
import by.it.selvanovich.weather.repository.DataRepository;
import by.it.selvanovich.weather.service.WeatherDataService;
import by.it.selvanovich.weather.service.impl.WeatherDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationControllerTest {

    private static final String REQUEST_BODY = "{" +
            "    \"from\": \"2023-12-15\"," +
            "    \"to\": \"2023-12-15\"" +
            "}";

    @Mock
    private WeatherDataServiceImpl service;

    @InjectMocks
    private ApplicationController controller;

    @Test
    void getWeatherShouldReturnResponseWithModel() {
        final WeatherDataModel weatherDataModel = mock(WeatherDataModel.class);
        try {
            when(service.getLastData("Minsk")).thenReturn(weatherDataModel);
            final ResponseEntity actual = controller.getWeather();
            final ResponseEntity entity = ResponseEntity.ok(weatherDataModel);
            assertNotNull(actual);
            assertEquals(entity, actual);
            verify(service).getLastData("Minsk");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAverageWeatherShouldReturnResponseWithModel() {
        final AverageWeatherDataModel weatherDataModel = mock(AverageWeatherDataModel.class);
        try {
            when(service.getAverageData(REQUEST_BODY, "Minsk")).thenReturn(weatherDataModel);
            final ResponseEntity actual = controller.getAverageWeather(REQUEST_BODY);
            final ResponseEntity entity = ResponseEntity.ok(weatherDataModel);
            assertNotNull(actual);
            assertEquals(entity, actual);
            verify(service).getAverageData(REQUEST_BODY, "Minsk");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}