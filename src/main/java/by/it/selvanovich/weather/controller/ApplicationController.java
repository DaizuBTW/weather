package by.it.selvanovich.weather.controller;


import by.it.selvanovich.weather.exeption.ServiceException;
import by.it.selvanovich.weather.service.impl.WeatherDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ApplicationController {

    @Autowired
    private WeatherDataServiceImpl service;

    @RequestMapping("weather/")
    public ResponseEntity getWeather() {
        try {
            return ResponseEntity.ok(service.getLastData("Minsk"));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e);

        }
    }

    @RequestMapping("average-weather/")
    public ResponseEntity getAverageWeather(@RequestBody(required = false) String body) {
        try {
            if (body == null) {
                return ResponseEntity.badRequest().body("An error has occurred! Enter the data");
            } else {
                return ResponseEntity.ok(service.getAverageData(body, "Minsk"));
            }
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }


    }
}
