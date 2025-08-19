package com.agri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agri.entity.AddEquipment;
import com.agri.entity.BookEqp;
import com.agri.entity.MBookEqp;
@Repository

public interface MBookEqpRepository extends  JpaRepository<MBookEqp,Long> {
	
		MBookEqp findByBookingno(String bookingno);
	Optional <MBookEqp> findByRandomid(String randno);
	
	 Iterable <MBookEqp> findByMyuser(String myuser);
	 Iterable <MBookEqp> findByVuser(String vuser);
}

