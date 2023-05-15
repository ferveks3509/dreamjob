package org.example.repository;

import org.example.model.City;

import java.util.Collection;

public interface CityRepository {
    Collection<City> findAll();
}
