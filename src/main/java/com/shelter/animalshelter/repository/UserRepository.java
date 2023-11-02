package com.shelter.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.shelter.animalshelter.model.User;


@Repository

public interface UserRepository extends CrudRepository <User, Long> {


}