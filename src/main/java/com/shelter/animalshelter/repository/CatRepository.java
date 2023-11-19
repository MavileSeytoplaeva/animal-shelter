package com.shelter.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shelter.animalshelter.model.animals.Cat;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findAllById(Long id); // Поиск котов по Id


}