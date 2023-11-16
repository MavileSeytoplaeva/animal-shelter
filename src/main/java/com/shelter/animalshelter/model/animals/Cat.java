package com.shelter.animalshelter.model.animals;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cat")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private Boolean isHealthy;

    @Column
    private Boolean vaccinated;
    @Column(name = "cat_id")
  private Long catId;

    @OneToMany(mappedBy = "catId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private long shelterId;

    //@Column(name = "shelter_id") // не надо
    //private Long shelterId;

   // public Cat(String name, Integer age, Boolean isHealthy, Boolean vaccinated, Long shelterId) {
   //     this.name = name;
   //     this.age = age;
   //     this.isHealthy = isHealthy;
   //     this.vaccinated = vaccinated;
   //     this.shelterId = shelterId;
   // }


    public Cat(Long id, String name, Integer age, Boolean isHealthy, Boolean vaccinated, Long catId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isHealthy = isHealthy;
        this.vaccinated = vaccinated;
        this.catId = catId;
    }

    @Override
    public String toString() {
        return "Имя: " + name +
                ", Возраст: " + age +
                ", Состояние здоровья: " + (isHealthy ? "здоров" : "инвалид") +
                ", Вакцинация: " + (vaccinated ? "привит" : "не привит");
    }
}
