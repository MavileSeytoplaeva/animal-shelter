package com.shelter.animalshelter.repository;


import com.shelter.animalshelter.model.shelters.CatShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.shelter.animalshelter.model.shelters.DogShelter;

import java.util.Optional;

@Repository
public interface DogSheltersRepository extends JpaRepository<DogShelter, Long> {

    Optional<DogShelter> findByName(String name);

    @Query(value = "SELECT * FROM dog_shelter", nativeQuery = true)
    DogShelter getAllInfo();

    @Query(value = "SELECT location FROM dog_shelter", nativeQuery = true)
    String getLocation();

    @Query(value = "SELECT safety_advice FROM dog_shelter", nativeQuery = true)
    String getSafetyAdvice();

    @Query(value = "SELECT timetable FROM dog_shelter", nativeQuery = true)
    String getTimetable();

    @Query(value = "SELECT security FROM dog_shelter", nativeQuery = true)
    String getSecurity();

    @Query(value = "SELECT about_me FROM dog_shelter", nativeQuery = true)
    String getAboutMe();


}
