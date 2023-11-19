package com.shelter.animalshelter.model;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "user_info_for_contact")
public class UserInfoForContact {
    @Id
    @Column(name = "chat_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private Long phoneNumber;
    @Column(name = "email")
    private String email;

//    Нужно будет позже добавить поле какое животное человек взял и связать эту таблицы с таблицей животного

    public UserInfoForContact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User Info For Contac{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoForContact that = (UserInfoForContact) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, email);
    }
}
