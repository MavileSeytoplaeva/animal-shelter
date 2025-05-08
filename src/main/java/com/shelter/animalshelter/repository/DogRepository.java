package com.shelter.animalshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shelter.animalshelter.model.animals.Dog;

import java.util.List;
@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

}
