package com.agri.controller;


import java.util.Optional;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agri.repository.NewUserRepository;
import com.agri.repository.NewVendorRepository;
import com.agri.repository.AddEquipmentRepo;
import com.agri.repository.BookEqpRepository;
import com.agri.entity.NewUser;

@Controller
public class NewUserController {

	@Autowired
	private NewUserRepository newuserrepo;
	
	@Autowired
	private   NewVendorRepository newvendorrepo;
	
	
	
	 @Autowired
	  private   AddEquipmentRepo eqprepo ;
	 
	 @Autowired
	  private   BookEqpRepository brepo;
	 
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	

	@GetMapping("/")
	public String home()
	{

		return "index";
	}
	
	
	
	

	
	
	@GetMapping("/userlogin")
	public String uloginform(Model model,HttpServletRequest request)
	{
		NewUser NewUser =new 	NewUser();
		model.addAttribute("NewUser",NewUser);			
		return "userlogin";
		
	}
	
	@PostMapping("/userLogin")
	public String uloginUser(@ModelAttribute("NewUser") 	NewUser 	NewUser,Model model,HttpServletRequest request,RedirectAttributes redirectAttrs)
	{
		

		HttpSession session =request.getSession();
		String userId=	NewUser.getUserId();
	  
		Optional<NewUser> userdata=newuserrepo.findById(userId);
	
		
	//User userdata=repo.findByUsername(userId);
	 //if(user.getPassword().equals(userdata.getPassword()))
		//if(user.getPassword().equals(userdata.get().getPassword()))
		
		
		
		
if(userdata.isPresent()) {
		    
	
	 if(userdata.get().getUserId().equals(NewUser.getUserId()) &&  	NewUser.getPassword().equals(userdata.get().getPassword()))
	{
		   
		   String name=	NewUser.getUserId();
		   	session.setAttribute("username", name);
		    String  username=(String) session.getAttribute("username");
		  	System.out.println(username);
		  	
		  		  	
		  	
		  	 String myname=	userdata.get().getName();
		  	session.setAttribute("myname", myname);
		    String  myusername=(String) session.getAttribute("myname");
		  	System.out.println(myusername);
		  	
			
		  	

		  	Long vcount=newvendorrepo.count();
		    Long ecount=eqprepo.count();
		  	Long bcount=brepo.countByMyuser(username);
		  	
		
		  	model.addAttribute("vcount",vcount);
		 	model.addAttribute("ecount",ecount);	
		  	model.addAttribute("bcount",bcount);
	  	
		  	
		  	return "usermenu";
	
		}

	else
	{
		//model.addAttribute("msg","Invalid User Name or Password");	
		//return "error";
		//redirectAttrs.addFlashAttribute("error", "Invalid User Name and Password");
		//return "ulogin";
		// return "login/error";
		 

		 return "redirect:/userlogin?error";
	}


	}
else
{

			 return "redirect:/userlogin?error";
	
	}


	
	} 
	
	
	 

		@RequestMapping("/userLogin")
		public String myamenu(Model model,HttpServletRequest request,HttpSession session)
		{
			  String  username=(String) session.getAttribute("username");
			  	System.out.println(username);
		  	
			  	
	
			  	Long vcount=newvendorrepo.count();
			    Long ecount=eqprepo.count();
			  	Long bcount=brepo.countByMyuser(username);
			  	
	
			  	model.addAttribute("vcount",vcount);
			 	model.addAttribute("ecount",ecount);	
			  	model.addAttribute("bcount",bcount);
			  	
			  	
		  	return "usermenu";
			
		}
		
		
		
	
		@GetMapping("/usermenu")
		public String usermenu(Model model,HttpServletRequest request,HttpSession session)
		
		{
			
		  	
			 String  username=(String) session.getAttribute("username");
			  	System.out.println(username);
		  	
			  	

			  	Long vcount=newvendorrepo.count();
			  	
			    Long ecount=eqprepo.count();
			  	Long bcount=brepo.countByMyuser(username);
			  	
	
			  	model.addAttribute("vcount",vcount);
			 	model.addAttribute("ecount",ecount);	
			  	model.addAttribute("bcount",bcount);
			return "usermenu";
			
		}

		
		
		
	
	@GetMapping("/userregister")
	public String userregister()
	{

		return "userregister";
	}
	
	
	
	
	
	
	@PostMapping("/NewRegister")
	public String register(@ModelAttribute("NewUser") NewUser u,HttpSession session)
	{
		

		String userId=	u.getUserId();
		String password=	u.getPassword();
		  
		Optional<NewUser> userdata=newuserrepo.findById(userId);
	
				
		 if(userdata.isPresent()) {    
			
			
				 return "redirect:/userregister?error";
			}
		 
		 else
		 {
		
		
		System.out.println(u);
				
		newuserrepo.save(u);
		
		
		
		
		String myuserid=	u.getUserId();
	   	session.setAttribute("myuserid",myuserid);
	   	String mypassword=	u.getPassword();
	   	session.setAttribute("mypassword", mypassword);
	   	String myphno=u.getContactnumber();
	   	session.setAttribute("myphno", myphno);

		
	
			
		//	emailService.sendEmail(email);
			
			try
			{
		
				
			MimeMessage message=javaMailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message);
			
			helper.setFrom("mukundbps335@gmail.com");
			helper.setSubject("Agri Equipment:  Registration");
			helper.setTo(u.getEmailto());
			helper.setText("Agri Equipment : User Registered Successfully."+"\n User Id Is :"+userId+"\nYour Password:"+password);
				
	
			javaMailSender.send(message);
			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
		
		
	//	session.setAttribute("message", "User Register Successfully....");
		return "redirect:/userregister?success";
	}
	}
	
	//recover password




	@GetMapping("/uforget")
		public String mypassword(Model model,HttpServletRequest request)
		{
			NewUser NewUser =new 	NewUser();
			model.addAttribute("NewUser",NewUser);	
			return "uforget";
			
		}



	@PostMapping("/urpassword")
	 public String recoverpasswordotp(Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) 

	{
	   
		 
		   String userId=	NewUser.getUserId();	 
		   
		   		 
		   
			 Optional<NewUser> userdata=newuserrepo.findById(userId);
		
			 if(userdata.isPresent()) {
		  if(userdata.get().getUserId().equals(NewUser.getUserId()))
		  {

			  String mypassword=userdata.get().getPassword();
			  String emailto=userdata.get().getEmailto();
			  		  
			  String myuserid=userdata.get().getUserId();
			   String phonenumber=userdata.get().getContactnumber();
			  
			  session.setAttribute("myuserid", myuserid);
			  session.setAttribute("phonenumber", phonenumber);
			  
			  
		 
			 System.out.println(myuserid);
				System.out.println(phonenumber);
				
		 
			
		   return "redirect:/uforgetotp";
			  
		  }
		  else
	    {
		
			  return "redirect:/uforget?error";
	    }
		}
			 
			 else
			 {
				  return "redirect:/uforget?error";
				 
			 }


	}
	
	 @GetMapping("/uforgetotp")
		public String mypasswordotp(Model model,HttpServletRequest request,HttpSession session)
		{
	
		   String  myuserid=(String) session.getAttribute("myuserid");
		  	System.out.println(myuserid);
		   
			return "uforgetotp";
			
		} 
	
	
	
	
	 @GetMapping("/urpasswordemail")
	 public String recoverpasswordemail(Model model,@ModelAttribute("NewUser") NewUser NewUser,HttpSession session) 

	{
	   
		 
		 String userId=	(String) session.getAttribute("myuserid");
		 System.out.println("my userid "+userId);
		 
		   		 
		   
			 Optional<NewUser> userdata=newuserrepo.findById(userId);
		
			 if(userdata.isPresent()) {
		  if(userdata.get().getUserId().equals(userId))
		  {

			  String mypassword=userdata.get().getPassword();
			  String emailto=userdata.get().getEmailto();
		 
			  String mymessage="Agri Equipment Rental  "+"\n user id :"+ userId+"\nYour Password is "+mypassword;
			  String mysubject="\n Agri Equipment Rental  - Password Recovery...";
			
		
			
		   
		   try
			{
		
			
			
			MimeMessage message=javaMailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message);
			helper.setFrom("mukundbps335@gmail.com");
			helper.setTo(emailto);
			helper.setSubject(mysubject);
			helper.setText(mymessage);
			javaMailSender.send(message);
			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		   return "redirect:/uforgetstatus";
			  
		  }
		  else
	    {
		
			  return "redirect:/uforget?error";
	    }
		}
			 
			 else
			 {
				  return "redirect:/uforget?error";
				 
			 }


	}
	
	   @GetMapping("/uforgetstatus")
			public String passwordstatus(Model model,HttpServletRequest request,HttpSession session)
			{
		
			   String  myuserid=(String) session.getAttribute("myuserid");
			  	System.out.println(myuserid);
			   
				return "uforgetstatus";
				
			}
		   
	
	
	
	 @GetMapping("uedit/{userid}")
	   public String showUpdateForm(@PathVariable("userid") String userid, Model model,NewUser 	NewUser,HttpSession session) {
		 
		 	    
		    
		 NewUser =newuserrepo.findById(userid)	  
	           .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + userid));
	     model.addAttribute("userlist",NewUser);
	       return "updateuserprofile";
	      // return "redirect:/updatepackage";
	   }

	     
	      


	   @PostMapping("userupdateprofile/{userid}")
	   public String updateUser(@PathVariable("userid")String userid,NewUser userlist , BindingResult result,
	       Model model,HttpSession session) {
	       if (result.hasErrors()) {
	      userlist.setUserId(userid);
	           return "updateuser";
	       }

	       String  username=(String) session.getAttribute("username");
	       System.out.println(username);
	     //  tblwashingpoints.setMyuser(username);       
	      

	    newuserrepo.save(userlist);
	      
	      
	    
	      // model.addAttribute("stafflist", carrepo.findAll());
	       

	   	 // model.addAttribute("tblwashingpoints",carrepo.findByMyuser(username));
	   	  
	     // return "add_whashing_list";
	    return "redirect:/usermenu";
	 	
	   }
	   
	   
	  
		
	
}
