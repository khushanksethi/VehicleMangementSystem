package com.example.VehicleManagementSystem.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.VehicleManagementSystem.model.AuthorityModel;
import com.example.VehicleManagementSystem.model.UserModel;
import com.example.VehicleManagementSystem.model.VehicleModel;
import com.example.VehicleManagementSystem.repository.AuthRepository;
import com.example.VehicleManagementSystem.repository.UserRepository;
import com.example.VehicleManagementSystem.repository.VehicleRepository;
import com.example.VehicleManagementSystem.service.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	AuthService authservice;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AuthRepository authRepo;
	
	@Autowired
	VehicleRepository vehicleRepo;
	
	//create new Authority or Update existing Authority	details
	@GetMapping("/createAuth")
	public @ResponseBody AuthorityModel saveOrUpdateAuthority(@RequestBody AuthorityModel authority,Model m) {
		return authservice.saveOrUpdateAuthority(authority,m);
	}
	
	//retrieve Authority details using Authority id
	@GetMapping("/getAuth")
	public @ResponseBody AuthorityModel getAuthority(@RequestParam("id") int id) {
		return authRepo.findById(id);	
	}
	
	//retrieve set of users which have their vehicles registered with given Authority using Authority id
	@GetMapping("/getUsersByAuth")
	public @ResponseBody Set<UserModel> getUsersByAuthority(@RequestParam("authority_id") int authId){
		return userRepo.getUsersByAuthId(authId);
	}
	
	//retrieve set of vehicles registered with given Authority using Authority id
	@GetMapping("/getVehiclesByAuth")
	public @ResponseBody Set<VehicleModel> getVehiclesByAuthority(@RequestParam("authority_id") int authId){
		return vehicleRepo.getVehiclesByAuthId(authId);
	}
	
	
}
