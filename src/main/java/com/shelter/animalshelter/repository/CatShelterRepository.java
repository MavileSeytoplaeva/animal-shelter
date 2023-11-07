package com.shelter.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shelter.animalshelter.model.shelters.CatShelter;

import java.util.Optional;


@Repository
public interface CatShelterRepository extends JpaRepository<CatShelter, Long> {

    Optional<CatShelter> findByName(String name);

}