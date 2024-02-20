package com.example.demo.Repository.Park;

import com.example.demo.Data.Park;

import java.util.Optional;

public interface ParkRepository {
    Iterable<Park> findAll();
    Optional<Park> findById(long id);
    Park savePark(Park park);
    Park updatePark(Park park);
    void deletePark(long id);
}
