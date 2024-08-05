package com.finalProject.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalProject.api.model.Owner;

public interface JpaOwnerRepository extends JpaRepository<Owner, Long>{

}
