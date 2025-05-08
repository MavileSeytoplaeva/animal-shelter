package com.shelter.animalshelter.repository;

import com.shelter.animalshelter.model.AnimalAdopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalAdopterRepository extends JpaRepository<AnimalAdopter, Long> {
}
