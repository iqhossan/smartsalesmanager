package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String Home(Model model) {
		model.addAttribute("title", "Home-Smart Sales Manager");
		return "home";
	}
	
	@GetMapping("/about")
	public String About(Model model) {
		model.addAttribute("title", "About-Smart Sales Manager");
		return "about";
	}
	
	@GetMapping("/signup")
	public String Signup(Model model) {
		model.addAttribute("title", "Sign-Smart Sales Manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@RequestMapping(value="/do_register", method=RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user, Model model, HttpSession session) {
		System.out.println(user);
		try {
			User result = this.userRepository.save(user);
			model.addAttribute("user",new User());
			session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));
			
			return "signup";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong !! "+e.getMessage(), "alert-danger"));
			return "signup";
		}
		
	}
	
}
