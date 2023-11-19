package com.shelter.animalshelter.model.animals;


import javax.persistence.*;

import com.shelter.animalshelter.model.shelters.DogShelter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dog")

public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dog_id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private Boolean isHealthy;

    @Column
    private Boolean vaccinated;

   // @Column(name = "shelter_id")
   // private Long shelterId;

    @OneToMany(mappedBy = "dogId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // private long shelterId;
    private List<DogShelter> list;

    public Dog(Long dog_id, String name, Integer age, Boolean isHealthy, Boolean vaccinated) {
        this.dog_id = dog_id;
        this.name = name;
        this.age = age;
        this.isHealthy = isHealthy;
        this.vaccinated = vaccinated;
    }

    @Override
    public String toString() {
        return "\n Имя: " + name +
                ", \n Возраст: " + age +
                ", \n Состояние здоровья: " + (isHealthy ? "здоров" : "инвалид") +
                ", \n Вакцинация: " + (vaccinated ? "привит" : "не привит");
    }
}