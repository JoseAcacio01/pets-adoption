package com.finalProject.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
public class Owner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String lastName;
	
	private String email;
	
	private int fonNumber;
	
	private String address;
	
	@OneToMany(targetEntity=Pet.class)
	private List<Pet> pets = new ArrayList<>();

	public void addDesign(Pet desing) {
		  pets.add(desing);
		  System.out.println(pets.toString());
	  }

}
