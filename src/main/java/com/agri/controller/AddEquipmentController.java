package com.agri.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.agri.repository.AddEquipmentRepo;

import com.agri.entity.AddEquipment;
import com.agri.entity.NewUser;





import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import java.nio.file.StandardCopyOption;


@Controller
public class AddEquipmentController {
	@Autowired
	  private   AddEquipmentRepo eqprepo ;
	
	
	
	
	
	 @GetMapping("add_view_eqp")
	    public String eqp_list(Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) {
	       //model.addAttribute("eqplist",eqprepo.findAll());
	               
	        
	        String  username=(String) session.getAttribute("username");
	       // System.out.println(username);
	       // model.addAttribute("students",username);
	        
		 
		// model.addAttribute("eqplist",eqprepo.findByVendorId(username));
	        
	        model.addAttribute("eqplist",eqprepo.findByVendorId(username));
		 
	        return "add_view_eqp";
	    }
	 
	 
	 
	 
	 @GetMapping("add_eqp")
	    public String eqpForm(AddEquipment AddEquipment,  Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) {
		  
		  		  
	        return "add_eqp";
	    }
	  
	 
	 
	 @PostMapping("addEqp")
	    public String addStudent(@ModelAttribute("NewUser") NewUser NewUser,HttpSession session,AddEquipment AddEquipment ,BindingResult result,@RequestParam MultipartFile img){
	    	
	 	    
	         String  username=(String) session.getAttribute("username");
	        System.out.println(username);
	               
	        System.out.println(AddEquipment);	
	        
	        
	        AddEquipment.setVendorId(username);
	        AddEquipment.setMyuser(username);
	        
	        




	        	AddEquipment im = new   AddEquipment ();
	        	im.setSphoto(img.getOriginalFilename());
			
			
			AddEquipment.setSphoto(img.getOriginalFilename());

		
				try {

					File saveFile = new ClassPathResource("static/img").getFile();

					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + img.getOriginalFilename());
					System.out.println(path);
					Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				} catch (Exception e) {
					e.printStackTrace();
				}
		
	        
	        
	       eqprepo.save(AddEquipment);
	    
	        return "redirect:add_view_eqp";
	    }
	  
	 @GetMapping("eqpdelete/{id}")
	 public String deleteeqp(@PathVariable("id") long id, Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) {
		 AddEquipment AddEquipment= eqprepo.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
	  eqprepo.delete(AddEquipment);
	  model.addAttribute("eqplist",eqprepo.findAll());
	  //  model.addAttribute("students",username);
	  

	 // model.addAttribute("bookinglist",carrepo.findByMyuser(username));
	  return "redirect:/add_view_eqp";
	 }
	 
	 

	   @GetMapping("eqpedit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
			 AddEquipment AddEquipment=eqprepo.findById(id)	  
	        .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
	  model.addAttribute("eqplist", AddEquipment);
	    return "updateeqp";
	   // return "redirect:/updatepackage";
	}

	  
	
	@PostMapping("update/{id}")
	public String updateeqp(@PathVariable("id") Long id,  AddEquipment AddEquipment, BindingResult result,
	    Model model,HttpSession session) {
	    if (result.hasErrors()) {
	    	//TutorDetails.setId(userId);
	        return "updateeqp";
	    }

	    String  username=(String) session.getAttribute("username");
	    System.out.println(username);
	  //  tblwashingpoints.setMyuser(username);       
	   

	    eqprepo.save(AddEquipment);
	   
	   
	 
	   // model.addAttribute("tblwashingpoints", carrepo.findAll());
	    

		 // model.addAttribute("tblwashingpoints",carrepo.findByMyuser(username));
		  
	  // return "add_whashing_list";
	    return "redirect:/add_view_eqp";

	}
	
	
	
	
	
	
		
		
		
		
		
	
	 
	 
	 
}
