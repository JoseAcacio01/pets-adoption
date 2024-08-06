package com.finalProject.api.visual;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.finalProject.api.data.JpaPetRepository;
import com.finalProject.api.model.Owner;
import com.finalProject.api.model.Pet;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes("owner")
public class DesingPetController {
	
	
	@Autowired
	private JpaPetRepository petsRepo;
	
	@ModelAttribute(name="pet")
	public Pet pet() {
		return new Pet();
	}
	
	@ModelAttribute(name="owner")
	public Owner order() {
		return new Owner();
	}
	
	@GetMapping("/createPet")
	public String viewCreatePet() {
		return "createPet";
	}
	
	@PostMapping("/createPet")
	public String createPet(@Valid @ModelAttribute(name="pet") Pet pet, Errors error) {
		if (error.hasErrors()) {
			log.info("hubo un error, intentelo de nuevo");
			return "createPet";
		}
		
		petsRepo.save(pet);
		System.out.print("save");
		return "redirect:/";
	}
	
	@GetMapping("/pets")
	public String showPetList(Model modelo, @ModelAttribute("owner") Owner owner) {
		modelo.addAttribute("pets", petsRepo.findAll().stream().filter(pet -> !owner.getPets().contains(pet)).toList());
		if(owner.getPets().size() != 0) {
			modelo.addAttribute("isDisabled", false);
		} else {
			modelo.addAttribute("isDisabled", true);
		}
		return "petList";
	}
	
	
	
	

}
