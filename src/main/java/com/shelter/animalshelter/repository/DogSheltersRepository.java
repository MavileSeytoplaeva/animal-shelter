package com.shelter.animalshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shelter.animalshelter.model.shelters.DogShelter;

import java.util.Optional;

@Repository
public interface DogSheltersRepository extends JpaRepository<DogShelter, Long> {

    Optional<DogShelter> findByName(String name);


}
