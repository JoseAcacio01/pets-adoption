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

import com.finalProject.api.data.JpaOwnerRepository;
import com.finalProject.api.data.JpaPestRepository;
import com.finalProject.api.model.Owner;
import com.finalProject.api.model.Pet;


@RestController
@RequestMapping("/api/owner")
public class OwnerControllerApi {
	
	@Autowired
	private JpaOwnerRepository ownerRepo;
	
	@GetMapping("/lists")
	public Iterable<Owner> allPets() {
		return ownerRepo.findAll();
		
	}
	
	@GetMapping("/pages/{num_paged}")
	public Iterable<Owner> allPets(@PathVariable int num_paged) {
		PageRequest pageRequest = PageRequest.of(num_paged, 5);
		return ownerRepo.findAll(pageRequest);
		
	}
	
	@GetMapping("/youngerPets")
	public Iterable<Owner> youngerPets() {
		PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("birthdate").descending());
		return ownerRepo.findAll(pageRequest); 
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.OK)
	public Owner createPet(@RequestBody Owner owner) {
		Owner save = ownerRepo.save(owner); 
		return save;
	}
	
	
	@DeleteMapping("/{delete_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePet(@PathVariable Long delete_id) {
		ownerRepo.deleteById(delete_id);	
	}
	
	@GetMapping("/searchById/{owner_id}")
	public ResponseEntity<Owner> SearchById(@PathVariable Long owner_id) {
		Optional<Owner> petId = ownerRepo.findById(owner_id);
		if(petId.isPresent()) {
			return new ResponseEntity<>(petId.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	

}
