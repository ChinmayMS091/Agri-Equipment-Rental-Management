package com.agri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agri.entity.NewUser;

@Repository
public interface NewUserRepository extends JpaRepository<NewUser, String> {


	NewUser 	findByUserId(String userId);

}

