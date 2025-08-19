package com.agri.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agri.entity.NewUser;
import com.agri.entity.NewVendor;
import com.agri.repository.NewVendorRepository;
import com.agri.repository.AddEquipmentRepo;

import com.agri.repository.BookEqpRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;

@Controller
public class NewVendorController {

	

	
	@Autowired
	private   NewVendorRepository newvendorrepo;
	
	 @Autowired
	  private   BookEqpRepository brepo;
	 
	
	 	
	 @Autowired
	  private   AddEquipmentRepo eqprepo ;
	 
		
	 @Autowired
	  private  NewVendorRepository vrepo ;
	 	

	
	@Autowired
    private JavaMailSender javaMailSender;
	
	
	

	@RequestMapping("/vondorLogin")
	public String myamenu(Model model,HttpServletRequest request,	HttpSession session)
	{
		  String  username=(String) session.getAttribute("username");
		  	System.out.println(username);
	  	Long vcount=vrepo.count();
	  	Long ecount=eqprepo.countByVendorId(username);
	  	Long bcount=brepo.countByVuser(username);
	  	
	  	
	model.addAttribute("vcount",vcount);
	model.addAttribute("ecount",ecount);	
	model.addAttribute("bcount",bcount);
	  	return "vendormenu";
		
	}
	
	
	@GetMapping("/vlogin")
	public String uloginform(Model model,HttpServletRequest request)
	{
		NewVendor  NewVendor =new 	NewVendor();
		model.addAttribute("NewVendor",NewVendor);			
		return "vlogin";
		
	}
	
	@PostMapping("/vondorLogin")
	public String vloginUser(@ModelAttribute("NewVendor") 	NewVendor 	NewVendor,Model model,HttpServletRequest request,RedirectAttributes redirectAttrs)
	{
		

		HttpSession session =request.getSession();
		String userId=	NewVendor.getUserId();
	  
		Optional<NewVendor> userdata=newvendorrepo.findById(userId);
	
		
	//User userdata=repo.findByUsername(userId);
	 //if(user.getPassword().equals(userdata.getPassword()))
		//if(user.getPassword().equals(userdata.get().getPassword()))
		if(userdata.isPresent()) {	
	
	if(userdata.get().getUserId().equals(NewVendor.getUserId()) && NewVendor.getPassword().equals(userdata.get().getPassword()))
	{
		   
		   String name=	NewVendor.getUserId();
		   	session.setAttribute("username", name);
		    String  username=(String) session.getAttribute("username");
		  	System.out.println(username);
		  	
		  	
		  	
		  	 String myname=	userdata.get().getVendorName();
			  	session.setAttribute("myname", myname);
			    String  myusername=(String) session.getAttribute("myname");
			  	System.out.println(myusername);
		  	
			  	
			  	

					  	Long vcount=vrepo.count();
					  	Long ecount=eqprepo.countByVendorId(username);
					  	Long bcount=brepo.countByVuser(username);
					  	
					  	
			  	model.addAttribute("vcount",vcount);
			 	model.addAttribute("ecount",ecount);	
			  	model.addAttribute("bcount",bcount);
			  	
			 return "vendormenu";
	
		}

	else
	{
		//model.addAttribute("message","Invalid User Name and Password");	
		//return "error";
		//redirectAttrs.addFlashAttribute("error", "Invalid User Name and Password");
		//return "ulogin";
		 return "redirect:/vlogin?error";
	}

	
	
	} 
		else
		{

					 return "redirect:/vlogin?error";
			
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/vendormenu")
	public String vendormenu(Model model,HttpServletRequest request,	HttpSession session)
	{
		String  username=(String) session.getAttribute("username");
	  	System.out.println(username);
  	Long vcount=vrepo.count();
  	Long ecount=eqprepo.countByVendorId(username);
  	Long bcount=brepo.countByVuser(username);
  	
  	
model.addAttribute("vcount",vcount);
model.addAttribute("ecount",ecount);	
model.addAttribute("bcount",bcount);


		return "vendormenu";
	}
	
	
	
	
	
	
	@GetMapping("/vendorregister")
	public String userregister()
	{

		return "vendorregister";
	}
	
	
	
	@PostMapping("/NewVendorRegister")
	public String register(@ModelAttribute("NewVendor") NewVendor u,HttpSession session)
	{
		String userId=	u.getUserId();
		String password=	u.getPassword();
		  
		Optional<NewVendor> userdata=newvendorrepo.findById(userId);
	
				
		 if(userdata.isPresent()) {    
			
			
				 return "redirect:/vendorregister?error";
			}
		 
		 
		 
		 
		 else
		 {
		
		
		System.out.println(u);
				
		newvendorrepo.save(u);
		
		
		String myuserid=	u.getUserId();
	   	session.setAttribute("myuserid",myuserid);
	   	String mypassword=	u.getPassword();
	   	session.setAttribute("mypassword", mypassword);
	   	String myphno=u.getVContactNumber();
	   	session.setAttribute("myphno", myphno);

			
		//	emailService.sendEmail(email);
			
			try
			{
		
				
			MimeMessage message=javaMailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message);
			
			helper.setFrom("mukundbps335@gmail.com");
			helper.setTo(u.getEmailto());
			helper.setSubject("Agri Equipment:  Vendor Registration");
			helper.setText("Agri Equipment : Vendor Registered Successfully."+"\n User Id Is :"+userId+"\nYour Password:"+password);
				
	
			javaMailSender.send(message);
			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
		
		
	//	session.setAttribute("message", "User Register Successfully....");
		return "redirect:/vendorregister?success";
	}
	}
	
	
	
	
	@GetMapping("/vforget")
	public String mypassword(Model model,HttpServletRequest request)
	{
		NewVendor NewVendor =new 	NewVendor();
		model.addAttribute("NewVendor",NewVendor);	
		return "vforget";
		
	}



@PostMapping("/vrpassword")
 public String recoverpasswordotp(Model model,@ModelAttribute("NewVendor") NewVendor NewVendor,HttpSession session) 

{
   
	 
	   String userId=	NewVendor.getUserId();	 
	   
	   		 
	   
		 Optional<NewVendor> userdata=newvendorrepo.findById(userId);
	
		 if(userdata.isPresent()) {
	  if(userdata.get().getUserId().equals(NewVendor.getUserId()))
	  {

		  String mypassword=userdata.get().getPassword();
		  String emailto=userdata.get().getEmailto();
		  
		  
		  
		  String myuserid=userdata.get().getUserId();
		   String phonenumber=userdata.get().getVContactNumber();
		  
		  session.setAttribute("myuserid", myuserid);
		  session.setAttribute("phonenumber", phonenumber);
		  
	
		  
		  session.setAttribute("myuserid", myuserid);
		  session.setAttribute("phonenumber", phonenumber);
		  
		  System.out.println(myuserid);
			System.out.println(phonenumber);
			
		
	   return "redirect:/vforgetotp";
		  
	  }
	  else
    {
	
		  return "redirect:/vforget?error";
    }
	}
		 
		 else
		 {
			  return "redirect:/vforget?error";
			 
		 }


}




@GetMapping("/vforgetotp")
public String mypasswordotp(Model model,HttpServletRequest request,HttpSession session)
{

   String  myuserid=(String) session.getAttribute("myuserid");
  	System.out.println(myuserid);
   
	return "vforgetotp";
	
} 









@GetMapping("/vrpasswordemail")
public String recoverpasswordemail(Model model,@ModelAttribute("NewVendor") NewVendor NewVendor,HttpSession session) 

{
  

	   
	   
		 
		 String userId=	(String) session.getAttribute("myuserid");
		 System.out.println("my userid "+userId);
		 
	   
		 Optional<NewVendor> userdata=newvendorrepo.findById(userId);
	
		 if(userdata.isPresent()) {
	  if(userdata.get().getUserId().equals(userId))
	  {

		  String mypassword=userdata.get().getPassword();
		  String emailto=userdata.get().getEmailto();
	 
		  String mymessage="Agri Equipment Rental  "+"\n user id :"+userId+"\nYour Password is "+mypassword;
		  String mysubject="\n Agri Equipment Rental   - Password Recovery For Vendor...";
		
	
		
	   
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
	   return "redirect:/vforgetstatus";
		  
	  }
	  else
   {
	
		  return "redirect:/vforget?error";
   }
	}
		 
		 else
		 {
			  return "redirect:/vforget?error";
			 
		 }


}


@GetMapping("/vforgetstatus")
public String passwordstatus(Model model,HttpServletRequest request,HttpSession session)
{

   String  myuserid=(String) session.getAttribute("myuserid");
  	System.out.println(myuserid);
   
	return "vforgetstatus";
	
}


@GetMapping("editvendor/{userId}")
public String showvendorProfileForm(@PathVariable("userId") String  userId, Model model) {
 NewVendor  NewVendor=newvendorrepo.findById(userId)	  
        .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + userId));
  model.addAttribute("vendorlist",  NewVendor);
    return "updatevendorprofile";
   // return "redirect:/updatepackage";
}


@PostMapping("updatevendor/{userId}")
public String updatevendorprofile(@PathVariable("userId") String userId,  NewVendor  NewVendor, BindingResult result,
    Model model,HttpSession session) {
    if (result.hasErrors()) {
    	//TutorDetails.setId(userId);
        return "updatevendorprofile";
    }

    String  username=(String) session.getAttribute("username");
    System.out.println(username);
  //  tblwashingpoints.setMyuser(username);       
   

    newvendorrepo.save( NewVendor);
   
   
 
   // model.addAttribute("tblwashingpoints", carrepo.findAll());
    

	 // model.addAttribute("tblwashingpoints",carrepo.findByMyuser(username));
	  
  // return "add_whashing_list";
    return "redirect:/vendormenu";

}


@GetMapping("viewvendor/{userId}")
public String vendorlistbyuserid(@PathVariable("userId") String  userId, Model model) {
 NewVendor  NewVendor=newvendorrepo.findById(userId)	  
        .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + userId));
  model.addAttribute("vlist",  NewVendor);
  return "eqp_vendor_list";
   // return "redirect:/updatepackage";
}




}
