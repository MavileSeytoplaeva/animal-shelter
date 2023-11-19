package com.shelter.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shelter.animalshelter.model.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

}
