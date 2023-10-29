package com.shelter.animalshelter.model;


import java.util.Objects;

@Entity
@Table(name = "guests")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "chat_id")
    private long chatId;
    private String username;
    private String lastname;

    public Guest (){

    }

    public Guest(long chatId, String username, String lastname) {
        this.chatId = chatId;
        this.username = username;
        this.lastname = lastname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return getId() == guest.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

