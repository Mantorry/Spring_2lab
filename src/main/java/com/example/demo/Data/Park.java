package com.example.demo.Data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Table("parklands")
public class Park {
    @Id
    private long id;
    @Min(value = 1, message = "Необходимо выбрать город!")
    private long city_id;
    @Size(min = 3, message = "Название парка не может быть меньше 3 символов!")
    private String name;
    @NotNull
    private boolean water_place;
    @NotNull(message = "Дата не может быть пустой!")
    private LocalDate build_date;
    @NotNull
    private City city;

    public Park(long city_id, String name, boolean water_place, LocalDate build_date, City city) {
        this.city_id = city_id;
        this.name = name;
        this.water_place = water_place;
        this.build_date = build_date;
        this.city = city;
    }
}
