package com.example.VehicleManagementSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.VehicleManagementSystem.model.AuthorityModel;

@Repository
public interface AuthRepository extends JpaRepository<AuthorityModel, Integer> {
	
	public AuthorityModel deleteById(int id);
	public AuthorityModel findById(int id);
	
}
