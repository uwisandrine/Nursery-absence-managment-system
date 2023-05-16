package com.nurseryAbsence.Controllers;





import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nurseryAbsence.Models.Models;
import com.nurseryAbsence.Models.Signup;
import com.nurseryAbsence.Repos.Kidrepo;
import com.nurseryAbsence.Repos.SignupRepo;
import com.nurseryAbsence.Services.Save;


@Controller
public class Controllers {
    @Autowired
	private Save services;
    @Autowired
   	private Kidrepo kidrepo;
    @Autowired
    private SignupRepo signupRepo;
	@RequestMapping("/")
	public String home() {
	
		return "signup";
		
	}
	@RequestMapping("/login")
	public String login() {
	
		return "login";
		
	}
	  @RequestMapping("/save")
	public String  createkid(@RequestParam("id") Integer id,@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,@RequestParam("classname") String classname,@RequestParam("levelname") String levelname,@RequestParam("date") String date,@RequestParam("attendence") String attendence) {
		
		Models kiddusers=new Models();
		
		kiddusers.setStid(id);
		kiddusers.setFname(firstname);
		kiddusers.setLname(lastname);
		kiddusers.setCname(classname);
		kiddusers.setLevel(levelname);
		kiddusers.setDate(date);
		kiddusers.setAtendance(attendence);
		services.savekid(kiddusers);
		
		
		
		return "redirect:/view";
}
	    @GetMapping("/edit/{id}")
	    public ModelAndView showUpdateForm(@PathVariable("id") Integer id) {
	        
	        Optional<Models> user = kidrepo.findById(id);
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName("update");
	        mav.addObject("allusers",user);

	        return mav;
	    }
	    @RequestMapping("/view")
	    public  ModelAndView homeafter(){
	        
	        return PageShow(1);
	    }
	    @PostMapping("/update/{id}")
	    public String updateUser(@PathVariable("id") String id,Models user) {
	        Models userAttributes=new Models();
	        userAttributes.setId(user.getId());
	        userAttributes.setStid(user.getStid());
	        userAttributes.setFname(user.getFname());
	        userAttributes.setLname(user.getLname());
	        userAttributes.setCname(user.getCname());
	        userAttributes.setLevel(user.getLevel());
	        userAttributes.setDate(user.getDate());
	        userAttributes.setAtendance(user.getAtendance());
	        Models updatedUser = services.updateUser(userAttributes);
	        return "redirect:/view";
	    }
	    @GetMapping("/delete/{id}")
	    public String deleteUser(@PathVariable("id") Integer id) {
	        Models user = kidrepo.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	        kidrepo.delete(user);
	        return "redirect:/view";
	    }
	    @RequestMapping("/sign")
	    public String createaccount (@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,@RequestParam("email") String email,@RequestParam("password") String password) {
	    	Signup newaccount= new Signup();
	    	newaccount.setFname(firstname);
	    	newaccount.setLname(lastname);
	    	newaccount.setEmail(email);
	    	newaccount.setPassword(password);
	    	services.createacccount(newaccount);
	    	
	    	return "login";	
	    }
	    @RequestMapping("/log")
	    public String createaccount (@RequestParam("email") String email,@RequestParam("password") String password){
	    	Signup user = null;
			
			try {
				user= signupRepo.findByEmail(email);
			}catch (Exception e) {
				System.out.println(e);
			}
				

			if(user!=null && user.getEmail().equals(email)&& user.getPassword().equals(password)) {
				
					return "addkid";
				}
				else {
					return "login";	
			}	
	    	
	    	
	    }
	    @GetMapping("/page/{pageNo}")
	    public  ModelAndView PageShow(@PathVariable (value = "pageNo") int pageNo){
	        ModelAndView mav=new ModelAndView();
	        int pageSize=3;
	        Page<Models> page=services.getPaginated(pageNo,pageSize);
	        List<Models> allusersdata=page.getContent();
	        mav.setViewName("display");
	        mav.addObject("currentPage",pageNo);
	        mav.addObject("totalPages",page.getTotalPages());
	        mav.addObject("totalItems",page.getTotalElements());
	        mav.addObject("displayAll",allusersdata);
	        return  mav;
	    }
}
