package com.finalProject.api.visual;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import com.finalProject.api.model.Owner;



@Controller
public class DesingHomeController {
	
	@GetMapping("/")
	public String Home() {
		return "home";
	}

}
