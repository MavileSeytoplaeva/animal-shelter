package com.shelter.animalshelter.model.shelters;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.shelter.animalshelter.model.animals.Cat;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cat_shelter")
public class CatShelter {
    // id для приюта

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Название приюта

    @Column
    private String name;
    // Адрес и схема проезда

    @Column
    private String location;

    // Расписание работы приюта

    @Column
    private String timetable;

    // О приюте

    @Column(name = "about_me")
    private String aboutMe;

    // Список животных в приюте

    @OneToMany(mappedBy = "shelterId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cat> list;

    // Способ связи с охраной

    @Column
    private String security;

    // Рекомендации о технике безопасности на территории приюта

    @Column(name = "safety_advice")
    private String safetyAdvice;

    //Конструктор для POST в БД


    public CatShelter(Long id, String name, String location, String timetable, String aboutMe, String security, String safetyAdvice) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.timetable = timetable;
        this.aboutMe = aboutMe;
        this.security = security;
        this.safetyAdvice = safetyAdvice;
    }

    //Конструктор для PUT в БД

    public CatShelter(String name, String location, String timetable,
                      String aboutMe, String security, String safetyAdvice) {
        this.name = name;
        this.location = location;
        this.timetable = timetable;
        this.aboutMe = aboutMe;
        this.security = security;
        this.safetyAdvice = safetyAdvice;
    }
}