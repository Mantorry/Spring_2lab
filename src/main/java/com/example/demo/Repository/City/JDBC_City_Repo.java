package com.example.demo.Repository.City;

import com.example.demo.Data.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBC_City_Repo implements CityRepository{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JDBC_City_Repo(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Iterable<City> findAll() {
        return jdbcTemplate.query("select * from cities", this::mapRowToCity);
    }

    @Override
    public Optional<City> findById(int id){
        List<City> result = jdbcTemplate.query("select * from cities where id = ?", this::mapRowToCity, id);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    private City mapRowToCity(ResultSet row, int rowNum) throws SQLException{
        return new City(row.getLong("id"), row.getString("name"), row.getString("status"), row.getInt("population"));
    }

    @Override
    public City saveCity(City city) {
        jdbcTemplate.update("insert into cities (name, status, population) values (?, ?, ?)",
                city.getName(),
                city.getStatus(),
                city.getPopulation());
        return city;
    }

    @Override
    public void deleteCity(long id){
        jdbcTemplate.update("delete from cities where id=?", id);
    }

    @Override
    public City updateCity(City city){
        jdbcTemplate.update("update cities set name=?,status=?,population=? where id=?",
                city.getName(), city.getStatus(), city.getPopulation(), city.getId());
        return city;
    }
}
