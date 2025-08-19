package com.agri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.agri.entity.NewVendor;



@Repository
public interface NewVendorRepository extends JpaRepository <NewVendor,String> {
	
//Iterable <NewVendor>  findByUserId(String vendorid);
	
	NewVendor  findByUserId(String vendorid);
}
