package com.finalProject.api.web;


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
import com.finalProject.api.model.Owner;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(path="/api/owner", produces="application/json")
@Tag(name = "owner", description = "the owner API")
public class OwnerControllerApi {
	
	@Autowired
	private JpaOwnerRepository ownerRepo;
	
	@GetMapping()
	public Iterable<Owner> allPets() {
		return ownerRepo.findAll();
		
	}
	
	@GetMapping("/pages/{num_paged}")
	public Iterable<Owner> allOwner(@PathVariable int num_paged) {
		PageRequest pageRequest = PageRequest.of(num_paged, 5);
		return ownerRepo.findAll(pageRequest);
		
	}
	
	@GetMapping("/youngerPets")
	public Iterable<Owner> youngerOwner() {
		PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("birthdate").descending());
		return ownerRepo.findAll(pageRequest); 
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Owner createOwner(@RequestBody Owner owner) { 
		return ownerRepo.save(owner);
	}
	
	
	@DeleteMapping("/{delete_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOwner(@PathVariable Long delete_id) {
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
