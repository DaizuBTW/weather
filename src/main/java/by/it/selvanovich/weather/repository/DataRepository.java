package by.it.selvanovich.weather.repository;

import by.it.selvanovich.weather.entity.WeatherData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface DataRepository extends CrudRepository<WeatherData, Long> {

    WeatherData findTop1ByLocationContainingOrderByDateDesc(String location);

    @Query("SELECT AVG( x.temperature ) FROM WeatherData x " +
            " WHERE x.date BETWEEN (?1) AND (?2) AND x.location LIKE (?3)")
    float findAvgTemperatureByDate(Timestamp from, Timestamp to, String location);

    @Query("SELECT AVG( x.wind ) FROM WeatherData x " +
            " WHERE x.date BETWEEN (?1) AND (?2) AND x.location LIKE (?3)")
    float findAvgWindByDate(Timestamp from, Timestamp to, String location);

    @Query("SELECT AVG( x.pressure ) FROM WeatherData x " +
            " WHERE x.date BETWEEN (?1) AND (?2) AND x.location LIKE (?3)")
    float findAvgPressureByDate(Timestamp from, Timestamp to, String location);

    @Query("SELECT AVG( x.humidity ) FROM WeatherData x " +
            " WHERE x.date BETWEEN (?1) AND (?2) AND x.location LIKE (?3)")
    int findAvgHumidityByDate(Timestamp from, Timestamp to, String location);

    @Query("SELECT x.condition FROM WeatherData x " +
            " WHERE x.date BETWEEN (?1) AND (?2) AND x.location LIKE (?3)" +
            " GROUP BY x.condition " +
            " ORDER BY COUNT(x.id) DESC " +
            " LIMIT 1 ")
    String findAvgConditionByDate(Timestamp from, Timestamp to, String location);

}
