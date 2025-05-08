package com.shelter.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.shelter.animalshelter.model.shelters.CatShelter;

import java.util.Optional;


@Repository
public interface CatShelterRepository extends JpaRepository<CatShelter, Long> {

    Optional<CatShelter> findByName(String name);

    @Query(value = "SELECT about_me FROM cat_shelter", nativeQuery = true)
    String getAboutMe();

    @Query(value = "SELECT location FROM cat_shelter", nativeQuery = true)
    String getLocation();

    @Query(value = "SELECT safety_advice FROM cat_shelter", nativeQuery = true)
    String getSafetyAdvice();

    @Query(value = "SELECT timetable FROM cat_shelter", nativeQuery = true)
    String getTimetable();

    @Query(value = "SELECT security FROM cat_shelter", nativeQuery = true)
    String getSecurity();

    @Query(value = "SELECT * FROM cat_shelter", nativeQuery = true)
    CatShelter getAllInfo();



}