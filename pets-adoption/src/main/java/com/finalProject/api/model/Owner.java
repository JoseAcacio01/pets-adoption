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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "owner")
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="Name is requered")
	private String name;
	
	@NotBlank(message="LastName is requered")
	private String lastName;
	
	@NotBlank(message="Email is requered")
	private String email;
	
	@Digits(integer=12, fraction=0, message="Invalid weight, max 9 integers")
	private int phoneNumber; 

	@NotBlank(message="Address is requered")
	private String address;

	@OneToMany(targetEntity = Pet.class)
	private List<Pet> pets = new ArrayList<>();

	public void addDesign(Pet desing) {
		pets.add(desing);
		System.out.println(pets.toString());
	}

}
