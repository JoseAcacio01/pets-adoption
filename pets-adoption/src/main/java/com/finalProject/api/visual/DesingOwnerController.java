package com.finalProject.api.visual;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.finalProject.api.data.JpaOwnerRepository;
import com.finalProject.api.data.JpaPestRepository;
import com.finalProject.api.model.Owner;
import com.finalProject.api.model.Pet;

@Controller
@RequestMapping("/owner")
@SessionAttributes("owner")
public class DesingOwnerController {
	
	@Autowired
	private JpaOwnerRepository ownerRepo;
	
	@Autowired
	private JpaPestRepository petRepo;
	
	@GetMapping("/create")
	public String viewCreateOwner() {
		return "createOwner";
	}
	
	@PostMapping
	public String createOwner(@ModelAttribute("owner") Owner owner) {
		ownerRepo.save(owner);
		System.out.print("save Owner");
		return "congratulations";
	}
	
	@GetMapping("/addToPetList/{id}") 
	public String addToPetList(@PathVariable Long id, @ModelAttribute("owner") Owner owner) {
		Optional<Pet> byId = petRepo.findById(id);
		System.out.print("paso por id");
		owner.addDesign(byId.get());		
		return "redirect:/pets";
	}
	
	

}
