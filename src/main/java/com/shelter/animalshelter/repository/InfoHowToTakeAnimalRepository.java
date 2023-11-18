package com.shelter.animalshelter.repository;

import com.shelter.animalshelter.model.InfoHowToTakeAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InfoHowToTakeAnimalRepository extends JpaRepository<InfoHowToTakeAnimal, Long> {
    @Query(value = "SELECT * FROM take_animal_info", nativeQuery = true)
    public InfoHowToTakeAnimal getALlInfo();
}
