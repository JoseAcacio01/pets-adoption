package com.finalProject.api.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalProject.api.data.JpaPestRepository;
import com.finalProject.api.model.Pet;

@RestController
@RequestMapping(path="/api/pets")
public class PetsControllerApi {
	
	
	@Autowired
	private JpaPestRepository petsRepo;
	
	@GetMapping("/lists")
	public Iterable<Pet> allPets() {
		return petsRepo.findAll();
		
	}
	
	@GetMapping("/pages/{num_paged}")
	public Iterable<Pet> allPets(@PathVariable int num_paged) {
		PageRequest pageRequest = PageRequest.of(num_paged, 5);
		return petsRepo.findAll(pageRequest);
		
	}
	
	@GetMapping("/youngerPets")
	public Iterable<Pet> youngerPets() {
		PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("birthdate").descending());
		return petsRepo.findAll(pageRequest); 
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.OK)
	public Pet createPet(@RequestBody Pet pet) {
		return petsRepo.save(pet); 
	}
	
	
	@DeleteMapping("/{delete_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePet(@PathVariable Long delete_id) {
		petsRepo.deleteById(delete_id);	
	}
	
	@GetMapping("/searchById/{pet_id}")
	public ResponseEntity<Pet> SearchById(@PathVariable Long pet_id) {
		Optional<Pet> petId = petsRepo.findById(pet_id);
		if(petId.isPresent()) {
			return new ResponseEntity<>(petId.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/searchByName/{pet_name}")
	public ResponseEntity<List<Pet>> searchByName(@PathVariable String pet_name) {
		List<Pet> petbyName = petsRepo.findByName(pet_name);
		if(petbyName.size() != 0) {
			return new ResponseEntity<>(petbyName, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/searchByRace/{pet_race}")
	public ResponseEntity<List<Pet>> searchByRace(@PathVariable String pet_race) {
		String low_pet_race = pet_race.toUpperCase();
		List<Pet> petbyRace = petsRepo.findByRace(low_pet_race);
		if(petbyRace.size() != 0) {
			return new ResponseEntity<>(petbyRace, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
		

}
