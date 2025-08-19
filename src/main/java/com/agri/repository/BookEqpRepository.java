package com.agri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agri.entity.BookEqp;
import com.agri.entity.MBookEqp;
@Repository
public interface BookEqpRepository extends  JpaRepository<BookEqp,Long> {
	
	BookEqp findByBookingno(String bookingno);
	
	
	
	 Iterable <BookEqp> findByMyuser(String myuser);
	 Iterable <BookEqp> findByVuser(String vuser);
	 
	 Long countByMyuser(String vuser);
	 Optional <MBookEqp> findByRandomid(String randno); 
	 
	 Long countByVuser(String vuser);
}
