package org.example.repository;

import org.example.model.City;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import java.util.Collection;

@Repository
public class Sql2oCityRepository implements CityRepository {

    private final Sql2o sql2o;

    public Sql2oCityRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Collection<City> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("select * from cities");
            return query.executeAndFetch(City.class);
        }
    }
}
