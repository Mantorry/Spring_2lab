package com.example.demo.Data;

import jakarta.validation.constraints.NotEmpty;
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
    @Size(min = 3, message = "Тип парка не может быть меньше 3 символов!")
    private String type;
    @NotNull
    private boolean water_place;
    @NotEmpty(message = "Дата не может быть пустой!")
    private String build_date;
    @NotNull
    private City city;

    public Park(long city_id, String name, String type, boolean water_place, String build_date, City city) {
        this.city_id = city_id;
        this.name = name;
        this.type = type;
        this.water_place = water_place;
        this.build_date = build_date;
        this.city = city;
    }
}
