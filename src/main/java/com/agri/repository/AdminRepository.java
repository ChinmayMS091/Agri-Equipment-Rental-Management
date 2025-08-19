package com.agri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.agri.entity.AdminUser;
public interface AdminRepository extends JpaRepository<AdminUser,String> {

	// User findByUsername(String userId);
		
	  //  User findUserByUserId(String userId);
		//@Query(value="select e from User u where userId=1 limit",nativeQuery=true)
		//User findUserByUserId(String userId);
		
	
	}