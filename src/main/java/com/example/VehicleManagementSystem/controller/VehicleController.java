package com.example.VehicleManagementSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.VehicleManagementSystem.model.InsuranceModel;
import com.example.VehicleManagementSystem.model.VehicleModel;
import com.example.VehicleManagementSystem.repository.InsuranceRepository;
import com.example.VehicleManagementSystem.repository.VehicleRepository;
import com.example.VehicleManagementSystem.service.VehicleService;

@RestController
public class VehicleController {
	
	@Autowired
	VehicleService vehicleservice;
	
	@Autowired
	VehicleRepository vehiclerepo;
	
	@Autowired
	InsuranceRepository insuranceRepo;
	
	//create new Vehicle or Update existing Vehicle details	
	@GetMapping("/createOrUpdateVehicle")
	public @ResponseBody VehicleModel saveOrUpdateVehicle(@RequestBody VehicleModel vehicle, Model m) {
		return vehicleservice.saveOrUpdateVehicle(vehicle, m);
	}
	
	//retrieve Vehicle details using Vehicle plate no.
	@GetMapping("/getVehicle")
	public @ResponseBody VehicleModel getVehicle(@RequestParam("plateNumber") String plateNumber) {
		return vehiclerepo.findVehicle(plateNumber);	
	}
	
	//delete Vehicle details using Vehicle plate no.
	@GetMapping("/delVehicle")
	public @ResponseBody VehicleModel deleteVehicle(@RequestParam("plateNumber") String plateNumber) {
		return vehiclerepo.deleteVehicle(plateNumber);	
	}
	
	//Retrieve insurance details mapped to vehicle
	@GetMapping("/insuranceDetails")
	public @ResponseBody InsuranceModel getinsuranceDetailsByVehicle(@RequestParam("plateNumber") String plateNumber) {
		return insuranceRepo.findInsuranceByVehicle(plateNumber);
	}
		
	//transfer vehicle ownership to different user
	@GetMapping("/transferVehicle")
	public @ResponseBody VehicleModel TransferVehicle(@RequestParam("plateNumber") String plateNumber, @RequestParam("newUserId") int newUserId, Model m) {
		return vehicleservice.TransferVehicle(plateNumber, newUserId, m);
	}
	
	
}
