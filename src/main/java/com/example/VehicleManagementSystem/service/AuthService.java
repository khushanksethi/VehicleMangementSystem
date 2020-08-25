package com.example.VehicleManagementSystem.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.example.VehicleManagementSystem.model.AuthorityModel;
import com.example.VehicleManagementSystem.repository.AuthRepository;

@Service
public class AuthService {
	
	@Autowired
	AuthRepository authRepo;
	
	public AuthorityModel saveOrUpdateAuthority(AuthorityModel auth, Model m){
		try {
			if(authRepo.findById(auth.getId()) != null ) {		//retrieve Authority details using Authority id from DB
				if(authRepo.save(auth)!=null) {		//update Authority details in DB based on given inputs
					String yes="Data updated";		
					m.addAttribute("authUpdation", yes);
				}
				else {
					String no="Data not updated";		
					 m.addAttribute("authUpdation", no);
				}
			}
			else {
				AuthorityModel newAuth=authRepo.save(auth);		//create new Authority if Authority details not found in DB
				if(newAuth!=null) {
					String yes="Data saved";
					m.addAttribute("authCreation", yes);
				}
				else {
					String no="Data not saved";
					 m.addAttribute("authCreation", no);
				}
			}	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return auth;
	}
	
	
}