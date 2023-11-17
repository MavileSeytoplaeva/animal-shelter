package com.shelter.animalshelter.repository;

import com.shelter.animalshelter.model.UserInfoForContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoForContactRepository extends JpaRepository<UserInfoForContact, Long> {
}
