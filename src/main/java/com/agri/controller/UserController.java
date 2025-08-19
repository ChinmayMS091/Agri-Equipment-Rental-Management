package com.agri.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agri.entity.AddEquipment;
import com.agri.entity.AdminUser;
import com.agri.entity.NewUser;
import com.agri.entity.NewVendor;
import com.agri.repository.AdminRepository;
import com.agri.repository.NewUserRepository;
import com.agri.repository.NewVendorRepository;

	@Controller
	public class UserController {
		@Autowired
		private AdminRepository repo;
		
		
		@Autowired
		private NewUserRepository urepo;
		
		
		@Autowired
		private NewVendorRepository vrepo;
		
		
		@RequestMapping("/adminLogin")
		public String myamenu(Model model,HttpServletRequest request)
		{

		  	Long ucount=urepo.count();
		  	Long vcount=vrepo.count();
		  	
		  	model.addAttribute("ucount",ucount);	
		  	model.addAttribute("vcount",vcount);
					
		  	return "adminmenu";
			
		}
		
		
		@GetMapping("/alogout")
		public String adminmenu(Model model,HttpServletRequest request)
		{
			
			return "index";
		}
		
		
		
		@GetMapping("/adminmenu")
		public String adminmenuhome(Model model,HttpServletRequest request)
		{
		  	Long ucount=urepo.count();
		  	Long vcount=vrepo.count();
		  	
		  	model.addAttribute("ucount",ucount);	
		  	model.addAttribute("vcount",vcount);
			
			return "adminmenu";
		}
		
		
		
		
		
		
		
		
		
		@GetMapping("/alogin")
		public String adminlogin(Model model,HttpServletRequest request)
		{
			AdminUser AdminUser =new AdminUser();
			model.addAttribute("AdminUser",AdminUser);	

			return "alogin";
		}
		
		
		
		
		
		
		
		
		@PostMapping("/adminLogin")
		public String loginUser(@ModelAttribute("user") AdminUser AdminUser,Model model,HttpServletRequest request)
		{
			

			HttpSession session =request.getSession();
			String userId=AdminUser.getUserId();
		  
			Optional<AdminUser> userdata=repo.findById(userId);
		
		 
		//User userdata=repo.findByUsername(userId);
		 //if(user.getPassword().equals(userdata.getPassword()))
			//if(user.getPassword().equals(userdata.get().getPassword()))
			
			if(userdata.isPresent()) {	
		
		if(userdata.get().getUserId().equals(AdminUser.getUserId()) && AdminUser.getPassword().equals(userdata.get().getPassword()))
		{
			   
			   String name=AdminUser.getUserId();
			   	session.setAttribute("username", name);
			    String  username=(String) session.getAttribute("username");
			  	System.out.println(username);
			  	
			  	
			  	 String myname=	userdata.get().getName();
				  	session.setAttribute("myname", myname);
				    String  myusername=(String) session.getAttribute("myname");
				  	System.out.println(myusername);
			  	
			  	
				  	Long ucount=urepo.count();
				  	Long vcount=vrepo.count();
				  	
				  	model.addAttribute("ucount",ucount);	
				  	model.addAttribute("vcount",vcount);
			  	
				 return "adminmenu";
		
			}
		else
		{
			//return "error";
			 return "redirect:/alogin?error";
		}

		
	}
			else
			{

						 return "redirect:alogin?error";
				
			}


				
		} 
		
		
		
		
		
		
		
		
		@GetMapping("view_vendor")
	    public String vendor_list(Model model,@ModelAttribute("AdminUser") AdminUser AdminUser,HttpSession session) {
	        model.addAttribute("vendorlist",vrepo.findAll());
	       // String  username=(String) session.getAttribute("username");
	       // System.out.println(username);
	       // model.addAttribute("students",username);
	        return "view_vendor";
	    }
		
		@GetMapping("view_user")
	    public String user_list(Model model,@ModelAttribute("AdminUser") AdminUser AdminUser,HttpSession session) {
	        model.addAttribute("userlist",urepo.findAll());
	       // String  username=(String) session.getAttribute("username");
	       // System.out.println(username);
	       // model.addAttribute("students",username);
	        return "view_user";
	    }

		
		
		
		
		
		 @GetMapping("userdelete/{userId}")
		 public String userdelete(@PathVariable("userId") String  userId, Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) {
			  NewUser= urepo.findById(userId)
		        .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + userId));
		  urepo.delete(NewUser);
		  model.addAttribute("userlist",urepo.findAll());
		  //  model.addAttribute("students",username);
		  

		 // model.addAttribute("bookinglist",carrepo.findByMyuser(username));
		  return "redirect:/view_user";
		 }
		 
		
		 
		 @GetMapping("useredit/{userId}")
			public String showUpdateForm(@PathVariable("userId") String  userId, Model model) {
			 NewUser  NewUser=urepo.findById(userId)	  
			        .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + userId));
			  model.addAttribute("userlist",  NewUser);
			    return "updateuser";
			   // return "redirect:/updatepackage";
			}

			  
			
			@PostMapping("userupdate/{userId}")
			public String updateuser(@PathVariable("userId") String userId,  NewUser  NewUser, BindingResult result,
			    Model model,HttpSession session) {
			    if (result.hasErrors()) {
			    	//TutorDetails.setId(userId);
			        return "updateuser";
			    }

			    String  username=(String) session.getAttribute("username");
			    System.out.println(username);
			  //  tblwashingpoints.setMyuser(username);       
			   

			    urepo.save(NewUser);
			   
			   
			 
			   // model.addAttribute("tblwashingpoints", carrepo.findAll());
			    

				 // model.addAttribute("tblwashingpoints",carrepo.findByMyuser(username));
				  
			  // return "add_whashing_list";
			    return "redirect:/view_user";

			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			 @GetMapping("vendordelete/{userId}")
			 public String vendordelete(@PathVariable("userId") String  userId, Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) {
				 NewVendor NewVendor= vrepo.findById(userId)
			        .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + userId));
			  vrepo.delete(NewVendor);
			  model.addAttribute("vendorlist",urepo.findAll());
			  //  model.addAttribute("students",username);
			  

			 // model.addAttribute("bookinglist",carrepo.findByMyuser(username));
			  return "redirect:/view_vendor";
			 }
			 
			
			 
			 @GetMapping("vendoredit/{userId}")
				public String showUpdateFormVendor(@PathVariable("userId") String  userId, Model model) {
				 NewVendor  NewVendor=vrepo.findById(userId)	  
				        .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + userId));
				  model.addAttribute("vendorlist",  NewVendor);
				    return "updatevendor";
				   // return "redirect:/updatepackage";
				}

				  
				
				@PostMapping("vendorupdate/{userId}")
				public String updatevendor(@PathVariable("userId") String userId,  NewVendor  NewVendor, BindingResult result,
				    Model model,HttpSession session) {
				    if (result.hasErrors()) {
				    	//TutorDetails.setId(userId);
				        return "updatevendor";
				    }

				    String  username=(String) session.getAttribute("username");
				    System.out.println(username);
				  //  tblwashingpoints.setMyuser(username);       
				   

				    vrepo.save( NewVendor);
				   
				   
				 
				   // model.addAttribute("tblwashingpoints", carrepo.findAll());
				    

					 // model.addAttribute("tblwashingpoints",carrepo.findByMyuser(username));
					  
				  // return "add_whashing_list";
				    return "redirect:/view_vendor";

				}
				
		
}
