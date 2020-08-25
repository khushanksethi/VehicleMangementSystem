package com.example.VehicleManagementSystem.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.example.VehicleManagementSystem.model.UserModel;
import com.example.VehicleManagementSystem.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	
	public UserModel saveOrUpdateUser(UserModel user, Model m){
		try {
			if(userRepo.findById(user.getId()) != null ) {	//retrieve user details using user id from DB
				if(userRepo.save(user)!=null) {		//update user details in DB based on given inputs
				String yes="Data updated";
				m.addAttribute("updation", yes);
				}
				else {
					String no="Data not updated";
					m.addAttribute("updation", no);
				}
			}
			else {
				UserModel newUser=userRepo.save(user);	//create new user if user details not found in DB
				if(newUser!=null) {
					String yes="Data saved";
					m.addAttribute("signup", yes);
				}
				else {
					String no="Data not saved";
					m.addAttribute("signup", no);
				}
			}	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return user;
	}
	
	
}