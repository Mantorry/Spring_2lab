package com.example.demo.Repository.City;
import com.example.demo.Data.City;

import java.util.Optional;

public interface CityRepository {
    Iterable<City> findAll();
    Optional<City> findById(long id);
    City saveCity(City city);
    City updateCity(City city);
    void deleteCity(long id);
}
