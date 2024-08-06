package com.finalProject.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
@Entity
@Table(name="pet")
public class Pet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Name is requered")
	private String name;
	
	
	private LocalDate birthdate;
	
	@NotBlank(message="Race is requered")
	private String race;
	
	@Digits(integer=4, fraction=0, message="Invalid weight, max 4 integers")
	private int weight;
	
	private Boolean hasChip;
		
	@NotBlank(message="urlFoto is requered")
	private String urlFoto;

	
	
}
