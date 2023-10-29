package com.shelter.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shelter.animalshelter.model.Guest;


@Repository

public interface GuestRepository extends JpaRepository <Guest, Long> {
    public Guest existsByChatId(long chatId);

}