package com.example.demo.Repository.Park;

import com.example.demo.Data.City;
import com.example.demo.Data.Park;
import com.example.demo.Repository.City.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBC_Park_Repo implements ParkRepository{
    private final JdbcTemplate jdbcTemplate;
    private final CityRepository cityRepository;
    @Autowired
    public JDBC_Park_Repo(JdbcTemplate jdbcTemplate, CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        this.jdbcTemplate = jdbcTemplate;}
    @Override
    public Iterable<Park> findAll() {return jdbcTemplate.query("select * from parklands", this::mapRowToPark);}
    @Override
    public Optional<Park> findById(int id) {
        List<Park> results = jdbcTemplate.query("select * from parklands where id=?", this::mapRowToPark, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
    private Park mapRowToPark(ResultSet row, int rowNum) throws SQLException {
        City select = cityRepository.findById(row.getInt("city_id")).get();
        return new Park(row.getLong("id"), select.getId(), row.getString("name"),
                row.getString("type"),row.getBoolean("water_place"),
                row.getTimestamp("build_date").toLocalDateTime().toLocalDate().toString(),
                select);
    }
    @Override
    public Park savePark(Park student) {
        jdbcTemplate.update(
                "insert into parlands (city_id, name, type, water_place, build_date) " +
                        "values (?, ?, ?, ?, ?)",
                student.getCity().getId(),
                student.getName(),
                student.getType(),
                student.isWater_place(),
                student.getBuild_date());
        return student;
    }

    @Override
    public void deletePark(long id){
        jdbcTemplate.update("delete from parklands where id=?", id);
    }

    @Override
    public Park updatePark(Park student){
        jdbcTemplate.update("update parklands set city_id=?,name?,type=?,water_place=?,build_date=? where id=?",
                student.getCity().getId(),
                student.getName(),
                student.getType(),
                student.isWater_place(),
                student.getBuild_date(),
                student.getId());
        return student;
    }
}
