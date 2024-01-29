package com.example.demo.Data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Table("cities")
public class City {
    @Id
    private long id;
    @Size(min = 3, message = "Название города не может быть меньше 3 символов!")
    private String name;
    @Size(min = 3, message = "Статус города не может быть меньше 3 символов!")
    private String status;
    @Min(value = 12000, message = "Население города не может быть меньше 12000 человек!")
    private int population;

    public City(String name, String status, int population) {
        this.name = name;
        this.status = status;
        this.population = population;
    }
}
