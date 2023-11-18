package com.shelter.animalshelter.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    private Long telegramId;

    @Column
    private String firstName;


    public User(Long telegramId, String firstName) {
        this.telegramId = telegramId;
        this.firstName = firstName;

    }
}

