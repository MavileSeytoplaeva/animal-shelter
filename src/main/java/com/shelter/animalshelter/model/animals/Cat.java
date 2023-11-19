package com.shelter.animalshelter.model.animals;

import javax.persistence.*;

import com.shelter.animalshelter.model.shelters.CatShelter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cat")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cat_id;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private Boolean isHealthy;
    @Column
    private Boolean vaccinated;



    @OneToMany(mappedBy = "catId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   // private long shelterId;
    private List<CatShelter> list;
    //@Column(name = "shelter_id") // не надо
    //private Long shelterId;

   // public Cat(String name, Integer age, Boolean isHealthy, Boolean vaccinated, Long shelterId) {
   //     this.name = name;
   //     this.age = age;
   //     this.isHealthy = isHealthy;
   //     this.vaccinated = vaccinated;
   //     this.shelterId = shelterId;
   // }


    public Cat(Long cat_id, String name, Integer age, Boolean isHealthy, Boolean vaccinated) {
        this.cat_id = cat_id;
        this.name = name;
        this.age = age;
        this.isHealthy = isHealthy;
        this.vaccinated = vaccinated;
    }

    @Override
    public String toString() {
        return "\n Имя: " + name +
                ",  \n Возраст: " + age +
                ", \n Состояние здоровья: " + (isHealthy ? "здоров" : "инвалид") +
                ", \n Вакцинация: " + (vaccinated ? "привит" : "не привит");
    }
}
