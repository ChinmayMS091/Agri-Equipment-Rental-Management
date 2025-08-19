package com.agri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.agri.entity.AddEquipment;
import com.agri.entity.NewUser;


@Repository
public interface AddEquipmentRepo extends JpaRepository<AddEquipment,Long> {
	
Iterable <AddEquipment> findByVendorId (String vendorId);
AddEquipment findByMyuser(String myuser);

AddEquipment findByRandomid(String randno);

Long countByVendorId (String vendorId);


//AddEquipment findByVendorId (String vendorId);

//Iterable <AddEquipment> findByMyuser(String myuser);
	
//	NewUser 	findByUserId(String userId);
	
}

