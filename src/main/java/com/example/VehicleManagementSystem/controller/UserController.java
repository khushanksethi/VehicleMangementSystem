package com.example.VehicleManagementSystem.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.VehicleManagementSystem.model.UserModel;
import com.example.VehicleManagementSystem.model.VehicleModel;
import com.example.VehicleManagementSystem.repository.UserRepository;
import com.example.VehicleManagementSystem.repository.VehicleRepository;
import com.example.VehicleManagementSystem.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	VehicleRepository vehicleRepo;
	
	//create new User or Update existing User details
	@GetMapping("/createUser")
	@ResponseStatus(HttpStatus.CREATED)
	public UserModel saveOrUpdateUser(@RequestBody UserModel user, Model m){
		return userservice.saveOrUpdateUser(user,m);
	}
	
	//retrieve User details using User id
	@GetMapping("/getUser")
	public @ResponseBody UserModel getUser(@RequestParam("userId") int userId) {
		return userRepo.findById(userId);
	}

	//retrieve user details using vehicle plate number
	@GetMapping("/getUserByVehicle")
	public @ResponseBody UserModel getUserByVehicle(@RequestParam("plateNumber") String plateNumber){
		return userRepo.findUserByVehicle(plateNumber);
	}
	
	//retrieve details of set of vehicles owned by given user 
	@GetMapping("/getVehiclesByUser")
	public @ResponseBody Set<VehicleModel> getVehiclesByUser(@RequestParam("userId") int userId){
		return vehicleRepo.getVehiclesByUser(userId);
	}
	
	
}
