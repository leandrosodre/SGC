package com.sgc.SGC.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String logout(Model model) {
		model.addAttribute("logout", true);
		return "login";	
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String erroLogin(Model model) {
		model.addAttribute("erroLogin", true);
		return "login";	
	}
}
