package com.finalProject.api.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalProject.api.model.Pet;

public interface JpaPestRepository extends JpaRepository<Pet, Long>{
	
	List<Pet> findByName(String name);
	
}
