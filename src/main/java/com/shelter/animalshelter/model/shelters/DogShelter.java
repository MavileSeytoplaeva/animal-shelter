package com.shelter.animalshelter.model.shelters;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.shelter.animalshelter.model.animals.Dog;

import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "dog_shelter")
public class DogShelter {
    //id для приюта

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Название приюта

    @Column
    private String name;
    // Адрес и схема проезда

    @Column
    private String location;
    //Расписание

    @Column
    private String timetable;
    // О приюте

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "dog_id")
    private Long dogId;

    // Список животных в приюте

   // @OneToMany(mappedBy = "shelterId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private List<Dog> list;
    // Способ связи с охраной

    @Column
    private String security;
    //Рекомендации о технике безопасности на территории приюта

    @Column(name = "safety_advice")
    private String safetyAdvice;

    // Конструктор для POST в БД

    public DogShelter(Long idShelter, String name, String location, String timetable, String aboutMe, String security, String safetyAdvice) {
        this.id = idShelter;
        this.name = name;
        this.location = location;
        this.timetable = timetable;
        this.aboutMe = aboutMe;
        this.security = security;
        this.safetyAdvice = safetyAdvice;
    }

    //Конструктор для PUT в БД

    public DogShelter(String name, String location, String timetable,
                      String aboutMe, String security, String safetyAdvice) {
        this.name = name;
        this.location = location;
        this.timetable = timetable;
        this.aboutMe = aboutMe;
        this.security = security;
        this.safetyAdvice = safetyAdvice;
    }
}
