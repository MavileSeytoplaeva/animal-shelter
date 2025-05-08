package com.shelter.animalshelter.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "animal_adopter")
public class AnimalAdopter {
    @Id
    @Column(name = "chat_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private Long phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "took_animal")
    private boolean tookAnimal;

}
