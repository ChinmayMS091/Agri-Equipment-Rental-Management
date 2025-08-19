package com.agri.controller;
import java.util.Optional;
import java.util.*;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.agri.repository.AddEquipmentRepo;
import com.agri.repository.BookEqpRepository;
import com.agri.repository.MBookEqpRepository;
import com.agri.repository.NewUserRepository;
import com.agri.repository.NewVendorRepository;
import com.agri.entity.BookEqp;
import com.agri.entity.MBookEqp;
import com.agri.entity.NewUser;
import com.agri.entity.NewVendor;

import com.agri.entity.AddEquipment;

@Controller
public class BookEqpController {
	 @Autowired
	  private   BookEqpRepository brepo;
	 
	 @Autowired
	  private   MBookEqpRepository mbrepo;
	 
	 
	 @Autowired
	  private   NewUserRepository urepo; //user data
	 

	 @Autowired
	  private   AddEquipmentRepo eqprepo ;
	 
		
	 @Autowired
	  private  NewVendorRepository vrepo ;
	 
	 
	 
	 
		@GetMapping("view_book_eqp")
		 public String view_eqp_list(Model model,@ModelAttribute("NewVendor") NewVendor NewVendor,HttpSession session) {
		    model.addAttribute("eqplist",eqprepo.findAll());
		  String  username=(String) session.getAttribute("username");
		   System.out.println(username);
		   
		   
		  // model.addAttribute("bookinglist",brepo.findByMyuser(username));
		   //  model.addAttribute("students",username);
		   
		  
		  // model.addAttribute("bookinglist",carrepo.findByMyuser(username));
		     return "view_book_eqp";
		 }
		   
		
		
		
		
		
		
		 
		@GetMapping("bookeqp/{randomid}")
		public String showBookForm(@PathVariable("randomid")  String   randomid, Model model,HttpSession session,
				AddEquipment AddEquipment) {
			
						

			  model.addAttribute("eqplist",eqprepo.findByRandomid(randomid));
			  			  			
			  AddEquipment AddEquipmentid=eqprepo.findByRandomid(randomid);
			  String vid= AddEquipmentid.getVendorId();
			  
		  String  username=(String) session.getAttribute("username");
		  System.out.println(username);
		  		  model.addAttribute("userdata",urepo.findByUserId(username));
		  		  	  		  
		  		 
		 		NewVendor NewVendor=vrepo.findByUserId(vid);
		
		 		model.addAttribute("vlist",NewVendor);
		 		
		 		Optional<MBookEqp> userdata=mbrepo.findByRandomid(randomid);
				
		 		if(userdata.isPresent()) {
					
					if(userdata.get().getRandomid().equals(randomid))
					{
						return "redirect:/view_book_eqp?error";
					}
					else
					{
					    return "bookeqp";
					
					}
					
				 }
		 		else
		 		{
			  
		    return "bookeqp";
		 		}
		   // return "redirect:/updatepackage";
		}

	
		
		
		@PostMapping("book/{userId}")
		public String updateTutor(@PathVariable("userId") String userId,BookEqp BookEqp,MBookEqp MBookEqp, BindingResult result,
		    Model model,HttpSession session) {
		    if (result.hasErrors()) {
		    	//TutorDetails.setId(userId);
		        return "updatetutor";
		    }

		    String  username=(String) session.getAttribute("username");
		    System.out.println(username);
		    
		    
		    
		
		  
		    BookEqp.setMyuser(username);       
		    MBookEqp.setMyuser(username); 

		    brepo.save(BookEqp);
		    mbrepo.save(MBookEqp);
		   
		   
		 
		   // model.addAttribute("tblwashingpoints", carrepo.findAll());
		    

			 // model.addAttribute("tblwashingpoints",carrepo.findByMyuser(username));
			  
		  // return "add_whashing_list";
		  
		    
		    //return "redirect:/view_book_eqp";
		    
		    return "redirect:/displaymybookingmsg";

		}
		
		@GetMapping("/displaymybookingmsg")
		public String mybookingmsg()
		{
			return "displaymybookingmsg";
		}
		
		
		
		
		
		
		


		@GetMapping("aveqp/{randomid}")
		public String Availability(@PathVariable("randomid")  String   randomid, Model model,HttpSession session,
				AddEquipment AddEquipment)
		{
			
			
			Optional<MBookEqp> userdata=mbrepo.findByRandomid(randomid);
			
			
			//MBookEqp MBookEqp=mbrepo.findByRandomid(randomid);	
			
			
			 if(userdata.isPresent()) {
			
			if(userdata.get().getRandomid().equals(randomid))
			{
				return "redirect:/view_book_eqp?error";
			}
			else
			{
				return "redirect:/view_book_eqp?success";
			}
			
			 }
			  
		  
		   // return "redirect:/updatepackage";
			 else {
				 return "redirect:/view_book_eqp?success"; 
			 }
			
		}
		
		
		

		 @GetMapping("displaymybooking")
		 public String displaymybooking(Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) {
		    // model.addAttribute("bookinglist",bookrepo.findAll());
		  String  username=(String) session.getAttribute("username");
		   System.out.println(username);
		  	   

		   model.addAttribute("displaymybooking",brepo.findByMyuser(username));
		   //  model.addAttribute("students",username);
		   
		  
		  // model.addAttribute("bookinglist",carrepo.findByMyuser(username));
		     return "displaymybooking";
		 }
		   

		 
		 
		 
		 @GetMapping("cancelbooking/{bookingno}")
		 public String cancelbooking(@PathVariable("bookingno") String  bookingno, Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) {
			
			 			 
			 BookEqp BookEqp= brepo.findByBookingno(bookingno);
		   
		  brepo.delete(BookEqp);
		  
		 
		  MBookEqp MBookEqp= mbrepo.findByBookingno(bookingno);
		  mbrepo.delete(MBookEqp);
		  
		  
		  model.addAttribute("eqplist",brepo.findAll());
		  //  model.addAttribute("students",username);
		   // model.addAttribute("bookinglist",carrepo.findByMyuser(username));
		  return "redirect:/displaymybooking";
		 }
		 
		
		 
		 @GetMapping("paybooking/{bookingno}")
		
			public String mybookingpay(@PathVariable("bookingno")  String   bookingno, Model model,HttpSession session,
					AddEquipment AddEquipment)		
			{
			 model.addAttribute("bookingdetails",brepo.findByBookingno(bookingno));
			 
			// Optional<BookEqp> userdata=brepo.findByRandomid(randomid);
			 
			BookEqp userdata= brepo.findByBookingno(bookingno);
			
			
			
				return "pay";
		
			}
		 
		 
		 
		 
		 
		 @GetMapping("cancelbookingv/{bookingno}")
		 public String cancelbookingv(@PathVariable("bookingno") String bookingno, String randomid,Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) {
				 
		  		

		  MBookEqp MBookEqp= mbrepo.findByBookingno(bookingno);
		  
		  mbrepo.delete(MBookEqp);
		  
		  
		  model.addAttribute("eqplist",mbrepo.findAll());
		  //  model.addAttribute("students",username);
		   // model.addAttribute("bookinglist",carrepo.findByMyuser(username));
		  
		  return "redirect:/availability";
	
		  
		  
		  
	
		 }
		 
		 
		 
		 
		 
		 
		 @GetMapping("booking")
		 public String managebooking(Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) {
		    // model.addAttribute("bookinglist",bookrepo.findAll());
		  String  username=(String) session.getAttribute("username");
		   System.out.println(username);
		  	   

		   model.addAttribute("displaybooking",brepo.findByVuser(username));
		   //  model.addAttribute("students",username);
		   
		  
		  // model.addAttribute("bookinglist",carrepo.findByMyuser(username));
		     return "booking";
		 }
		 
		 
		 

		 @GetMapping("availability")
		 public String availability(Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) {
		    // model.addAttribute("bookinglist",bookrepo.findAll());
		  String  username=(String) session.getAttribute("username");
		   System.out.println(username);
		  	   

		   model.addAttribute("displaybooking",mbrepo.findByVuser(username));
		   //  model.addAttribute("students",username);
		   
		  
		  // model.addAttribute("bookinglist",carrepo.findByMyuser(username));
		     return "availability";
		 }
		 
		 
		 

		 @GetMapping("eqpeditstatus/{id}")
			public String bookUpdateForm(@PathVariable("id") long id, Model model) {
					 BookEqp BookEqp=brepo.findById(id)	  
			        .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
			  model.addAttribute("bookinglist", BookEqp);
			    return "updatebookingform";
			   // return "redirect:/updatebookingform";
			}

			  
			
			@PostMapping("bookingupdate/{id}")
			public String updateeqp(@PathVariable("id") Long id,   BookEqp   BookEqp , BindingResult result,
			    Model model,HttpSession session) {
			    if (result.hasErrors()) {
			    	//TutorDetails.setId(userId);
			        return "updatebookingform";
			    }

			    String  username=(String) session.getAttribute("username");
			    System.out.println(username);
			  //  tblwashingpoints.setMyuser(username);       
			   

			   brepo.save(BookEqp );
			   
			   
			 
			   // model.addAttribute("tblwashingpoints", carrepo.findAll());
			    

				 // model.addAttribute("tblwashingpoints",carrepo.findByMyuser(username));
				  
			  // return "add_whashing_list";
			   // return "redirect:/booking";
			   return "redirect:/bookingmsg";
			}
			
			
			@GetMapping("/bookingmsg")
			public String bookingmsg()
			{
				return "bookingmsg";
			}
			
			
			 
		 
		   
	 
}
